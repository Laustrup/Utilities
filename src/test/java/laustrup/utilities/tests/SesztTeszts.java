package laustrup.utilities.tests;

import laustrup.utilities.models.users.sub_users.bands.Artist;
import laustrup.utilities.collections.sets.Seszt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

public class SesztTeszts extends UtilityTester {

    @Test
    public void canGetByIndex() {
        test(t -> {
            Artist expected = (Artist) arrange(e -> {
                _seszt = new Seszt<>(_adding);
                return _adding;
            });

            Artist actual = (Artist) act(e -> _seszt.get(0));

            asserting(expected,actual);

            actual = (Artist) act(e -> _seszt.Get(1));

            asserting(expected,actual);

            return end("canGetByIndex");
        });
    }

    @Test
    public void canGetByKey() {
        test(t -> {
            Artist expected = (Artist) arrange(e -> {
                _seszt = new Seszt<>(_adding);
                return _adding;
            });

            Artist actual = (Artist) act(e -> _seszt.get(expected.toString()));

            asserting(expected,actual);

            return end("canGetByKey");
        });
    }

    @Test
    public void canGet() {
        test(t -> {
            Artist expected = (Artist) arrange(e -> {
                _seszt = new Seszt<>(_adding);
                return _adding;
            });

            Artist actual = (Artist) act(e -> _seszt.get(expected));

            asserting(expected,actual);

            return end("canGet");
        });
    }

    @Test
    public void canContain() {
        test(t -> {
            arrange(e -> _seszt = new Seszt<>(new Object[]{ _adding }));

            asserting((boolean) act(e -> _seszt.contains(_adding)));

            return end("canContain");
        });
    }

    @Test
    public void canContainMultiple() {
        test(t -> {
            arrange(e -> _seszt = new Seszt<>(_addings));

            asserting((boolean) act(e -> _seszt.contains(_addings)));

            return end("canContainMultiple");
        });
    }

    @Test
    public void canInitiateWithInputs() {
        test(t -> {
            Object[] expectations = (Object[]) arrange(e -> _addings);

            act(e -> _seszt = new Seszt<>(expectations));

            asserting(_seszt.contains(expectations));

            return end("canInitiateWithInputs");
        });
    }

    @Test
    public void canAdd() {
        test(t -> {
            Object expected = arrange(e -> {
                _seszt = new Seszt<>();
                return _adding;
            });

            act(e -> _seszt.add(expected));

            asserting(_seszt.size() == 1);

            return end("canAdd");
        });
    }

    @Test
    public void canAddUniques() {
        test(t -> {
            Artist[] expectations = (Artist[]) arrange(e -> {
                _seszt = new Seszt<>(_addings);
                return _seszt.get_data();
            });

            act(e -> _seszt.add(expectations));

            asserting(_seszt.contains(expectations));

            return end("canAddUniques");
        });
    }

    @Test
    public void canOnlyAddUnique() {
        test(t -> {
            Artist[] arrangement = (Artist[]) arrange(e -> {
                Artist[] inputs = new Artist[]{(Artist) _adding, (Artist) _adding};
                _seszt = new Seszt<>(inputs);
                return inputs;
            });

            act(e -> _seszt.add(arrangement));

            asserting(_seszt.size() == 1);

            return end("canNotAddSame");
        });
    }

    @Test
    public void canClear() {
        test(t -> {
            arrange(e -> _seszt = new Seszt<>(_addings));
            boolean elementsIsAdded = !_seszt.isEmpty();

            act(e -> {
                _seszt.clear();
                return null;
            });

            asserting(_seszt.size() == 0 && elementsIsAdded);

            return end("canClear");
        });
    }

    @Test
    public void canRemove() {
        test(t -> {
            arrange(e -> _seszt = new Seszt<>(_addings));

            act(e -> _seszt.remove(_addings[1]));

            asserting(!_seszt.contains(_addings[1]));

            return end("canRemove");
        });
    }

    @Test
    public void canRemoveMultiple() {
        test(t -> {
            arrange(e -> _seszt = new Seszt<>(_addings));

            act(e -> _seszt.remove(new Object[]{_addings[0],_addings[2]}));

            asserting(!_seszt.contains(_addings[0]) && !_seszt.contains(_addings[2]));

            return end("canRemoveMultiple");
        });
    }

    @Test
    public void canRetain() {
        test(t -> {
            Artist[] expecations = (Artist[]) arrange(e -> {
                _seszt = new Seszt<>(_addings);
                Artist[] retains = new Artist[_seszt.size() - 1];
                for (int i = 0; i < retains.length; i++)
                    retains[i] = (Artist) _seszt.get(i);

                return retains;
            });

            act(e -> _seszt.retain(expecations));

            asserting(expecations,_seszt.get_data());

            return end("canRetain");
        });
    }

    @Test
    public void canSet() {
        String editedRunner = "This is a new runner";

        test(t -> {
            Artist[] arrangement = (Artist[]) arrange(e -> {
                _seszt = new Seszt<>(_addings);
                Artist[] storage = new Artist[2];
                storage[0] = (Artist) _seszt.get(0);
                Artist edit = (Artist) _seszt.get(0);
                edit.set_runner(editedRunner);
                storage[1] = edit;

                return storage;
            });

            act(e -> _seszt.set(arrangement[0], arrangement[1]));

            asserting(
                _seszt.contains(arrangement[1])
                && ((Artist) _seszt.get(0)).get_runner().equals(editedRunner)
            );

            return end("canSet");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1"+_delimiter+"2",
        "2"+_delimiter+"1",
        "2"+_delimiter+"2",
        "-1"+_delimiter+"-1",
        "-1"+_delimiter+"1",
        "1"+_delimiter+"-1",
    }, delimiter = _delimiter)
    public void canSetMultiple(int originalAmount, int replacementAmount) {
        String editedRunner = "This is a new runner";
        Set<Integer> indexes = new HashSet<>();

        test(t -> {
            Artist[] replacements = (Artist[]) arrange(e -> {
                int index;
                Artist[] originals = new Artist[originalAmount >= 0 ? originalAmount : 1];
                if (originalAmount < 0)
                    originals[0] = null;
                else
                    for (int i = 0; i < originalAmount; i++) {
                        do {
                            index = _random.nextInt(_items.get_artistAmount());
                        } while (indexes.contains(index));
                        indexes.add(index);

                        originals[i] = _items.get_artists()[index];
                    }

                _seszt = new Seszt<>(originals);

                Artist[] toReplace = new Artist[replacementAmount >= 0 ? replacementAmount : 1];
                if (replacementAmount < 0)
                    toReplace[0] = null;
                else
                    for (int i = 0; i < replacementAmount; i++) {
                        do {
                            index = _random.nextInt(_items.get_artistAmount());
                        } while (indexes.contains(index));
                        indexes.add(index);

                        toReplace[i] = _items.get_artists()[index];
                        toReplace[i].set_runner(editedRunner);
                    }

                return toReplace;
            });

            act(e -> _seszt.set(_seszt.get_data(), replacements));

            asserting(replacements, _seszt.get_data());

            return end("canSetMultiple");
        });
    }
}
