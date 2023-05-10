package laustrup.utilities.collections.sets;

import laustrup.models.users.sub_users.bands.Artist;
import laustrup.utilities.UtilityTester;
import laustrup.utilities.collections.sets.Seszt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

public class SesztTeszts extends UtilityTester {

    @Test
    void canGetByIndex() {
        test(() -> {
            Artist expected = (Artist) arrange(() -> {
                _seszt = new Seszt<>(_artist);
                return _artist;
            });

            Artist actual = (Artist) act(() -> _seszt.get(0));

            asserting(expected,actual);

            actual = (Artist) act(() -> _seszt.Get(1));

            asserting(expected,actual);
        });
    }

    @Test
    void canGetByKey() {
        test(() -> {
            Artist expected = (Artist) arrange(() -> {
                _seszt = new Seszt<>(_artist);
                return _artist;
            });

            Artist actual = (Artist) act(() -> _seszt.get(expected.toString()));

            asserting(expected,actual);

        });
    }

    @Test
    void canGet() {
        test(() -> {
            Artist expected = (Artist) arrange(() -> {
                _seszt = new Seszt<>(_artist);
                return _artist;
            });

            Artist actual = (Artist) act(() -> _seszt.get(expected));

            asserting(expected,actual);
        });
    }

    @Test
    void canContain() {
        test(() -> {
            arrange(() -> {
                _seszt = new Seszt<>(new Artist[]{_artist});
                return _seszt;
            });

            act(() -> _expected = _seszt.contains(_artist));

            asserting((boolean) _expected);
        });
    }

    @Test
    void canContainMultiple() {
        test(() -> {
            arrange(() -> {
                _seszt = new Seszt<>(_artists);
                return _seszt;
            });

            act(() -> _expected = _seszt.contains(_artists));

            asserting((boolean) _expected);

        });
    }

    @Test
    void canInitiateWithInputs() {
        test(() -> {
            Artist[] expectations = (Artist[]) arrange(() -> _artists);

            act(() -> _seszt = new Seszt<>(expectations));

            asserting(_seszt.contains(expectations));

        });
    }

    @Test
    void canAdd() {
        test(() -> {
            Artist expected = (Artist) arrange(() -> {
                _seszt = new Seszt<>();
                return _artist;
            });

            act(() -> _seszt.add(expected));

            asserting(_seszt.size() == 1);
        });
    }

    @Test
    void canAddUniques() {
        test(() -> {
            Artist[] expectations = (Artist[]) arrange(() -> {
                _seszt = new Seszt<>(new Artist[]{_artists[0], _artists[1]});
                return new Artist[]{_artist, _artists[2]};
            });

            act(() -> _seszt.add(expectations));

            asserting(_seszt.contains(expectations));
        });
    }

    @Test
    void canOnlyAddUnique() {
        test(() -> {
            Artist[] arrangement = (Artist[]) arrange(() -> {
                _seszt = new Seszt<>(new Artist[]{_artists[0], _artists[1]});
                return new Artist[]{_artists[1], _artists[2]};
            });

            act(() -> _seszt.add(arrangement));

            asserting(_seszt.size() == 3);
        });
    }

    @Test
    void canClear() {
        test(() -> {
            arrange(() -> _seszt = new Seszt<>(_artists));

            act(() -> {
                _seszt.clear();
                return _seszt;
            });

            asserting(_seszt.size() == 0);
        });
    }

    @Test
    void canRemove() {
        test(() -> {
            arrange(() -> _seszt = new Seszt<>(_artists));

            act(() -> _seszt.remove(_artists[1]));

            asserting(!_seszt.contains(_artists[1]));
        });
    }

    @Test
    void canRemoveMultiple() {
        test(() -> {
            arrange(() -> _seszt = new Seszt<>(_artists));

            act(() -> _seszt.remove(new Artist[]{_artists[0],_artists[2]}));

            asserting(!_seszt.contains(_artists[0]) && !_seszt.contains(_artists[2]));
        });
    }

    @Test
    void canRetain() {
        test(() -> {
            Artist[] expectations = (Artist[]) arrange(() -> {
                _seszt = new Seszt<>(_artists);
                Artist[] retains = new Artist[_seszt.size() - 1];
                for (int i = 0; i < retains.length; i++)
                    retains[i] = _seszt.get(i);

                return retains;
            });

            act(() -> _seszt.retain(expectations));

            asserting(expectations,_seszt.get_data());
        });
    }

    @Test
    void canSet() {
        String editedRunner = "This is a new runner";

        test(() -> {
            Artist[] arrangement = (Artist[]) arrange(() -> {
                _seszt = new Seszt<>(_artists);
                Artist[] storage = new Artist[2];
                storage[0] = _seszt.get(0);
                Artist edit = _seszt.get(0);
                edit.set_runner(editedRunner);
                storage[1] = edit;

                return storage;
            });

            act(() -> _seszt.set(arrangement[0], arrangement[1]));

            asserting(
                _seszt.contains(arrangement[1])
                && (_seszt.get(0)).get_runner().equals(editedRunner)
            );
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
    void canSetMultiple(int originalAmount, int replacementAmount) {
        String editedRunner = "This is a new runner";
        Set<Integer> indexes = new HashSet<>();

        test(() -> {
            Artist[] replacements = (Artist[]) arrange(() -> {
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

            act(() -> _seszt.set(_seszt.get_data(), replacements));

            asserting(replacements, _seszt.get_data());
        });
    }
}
