package laustrup.utilities.tests;

import laustrup.utilities.models.users.User;
import laustrup.utilities.models.users.sub_users.bands.Band;
import laustrup.utilities.collections.lists.Liszt;
import laustrup.utilities.console.Printer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LisztTeszts extends Tester<Object, Object> {

    /** The Liszt that will be used in the tests for testing. */
    private Liszt<Object> _liszt;

    @ParameterizedTest
    @CsvSource(value = {"true", "false"}, delimiter = '|')
    public void constructorTest(boolean isEmptyDataTemplate) {
        test(t -> {
            arrange();

            if (isEmptyDataTemplate) {
                act(e -> _liszt = new Liszt<>());

                asserting(_liszt.isEmpty());
            }
            else {
                act(e -> _liszt = new Liszt<>(new Object[]{true,false}));

                asserting(true, (_liszt.Get(1)));
                asserting(false, (_liszt.Get(2)));
            }

            return true;
        });
    }

    @Test
    public void canAddSingleElement() {
        test(t -> {
            Object expected = arrange(e -> {
                _liszt = new Liszt<>();
                return 1;
            });

            act(expected, e -> _liszt.add(e));

            asserting(expected, _liszt.Get(1));

            return true;
        });
    }

    @ParameterizedTest
    @CsvSource(value = {"true","false"})
    public void canAddAnArray(boolean constructorWithArgument) {
        test(t -> {
            arrange(constructorWithArgument, e -> {
                _liszt = (Boolean) e ?
                new Liszt<>(_items.get_bands())
                : new Liszt<>();
                return _liszt;
            });

            act(constructorWithArgument, e -> {
                if (!((Boolean) e))
                    _liszt.add(_items.get_bands());
                return _liszt;
            });

            for (Band band : _items.get_bands())
                asserting(band, _liszt.Get(band.toString()));

            for (int i = 1; i <= _items.get_bands().length; i++)
                asserting(_items.get_bands()[i-1], _liszt.Get(i));

            return true;
        });

    }

    @Test
    public void canRemove() {
        test(t -> {
            User expected = (User) arrange(e -> {
                User user = _items.generateUser();
                _liszt = new Liszt<>(new Object[]{user});
                return user;
            });

            act(expected, e -> _liszt.remove(e));

            asserting(_liszt.isEmpty());

            return true;
        });
    }

    @Test
    public void canRemoveMultiple() {
        test(t -> {
            Band[] expectations = (Band[]) arrange(e -> {
                Band[] bands = _items.get_bands();
                _liszt = new Liszt<>(bands);
                return bands;
            });

            act(expectations,element -> _liszt.remove(element));

            asserting(_liszt.isEmpty());

            return true;
        });
    }

    @Test
    public void canSet() {
        test(t -> {
            Object expected = arrange(e -> {
                _liszt.add(_addings[0]);
                return _addings[1];
            });

            Object actual = act(e -> _liszt.set(_addings[0], expected));

            asserting(expected,actual);

            return true;
        });

        test(t -> {
            Object expected = arrange(e -> {
                _liszt.add(_addings[0]);
                return _addings[1];
            });

            Object actual = act(e -> _liszt.set(1, expected));

            asserting(expected,actual);

            return true;
        });
    }

    @ParameterizedTest
    @CsvSource(value = {"0"+_divider+"0","1"+_divider+"0","0"+_divider+"1","3"+_divider+"0","0"+_divider+"3",
                        "1"+_divider+"1","3"+_divider+"1","1"+_divider+"3","3"+_divider+"3"}, delimiter = _delimiter)
    public void canSetMultiple(int elementsSize, int replacementsSize) {
        Object[] elements = new Object[elementsSize],
                 replacements = new Object[replacementsSize];
        _liszt = new Liszt<>();

        test(t -> {
            arrange(e -> {
                for (int i = 0; i < elementsSize; i++)
                    elements[i] = _items.get_bands()[_random.nextInt(_items.get_bandAmount())];
                for (int i = 0; i < replacementsSize; i++)
                    replacements[i] = _items.get_bands()[_random.nextInt(_items.get_bandAmount())];

                _liszt.add(elements);
                return Printer.get_instance().toString(_liszt.get_data());
            });

            act(e -> _liszt.set(elements, replacements));

            for (int i = 0; i < _liszt.size(); i++) {
                Band actual = (Band) _liszt.get(i),
                     expected = (Band) replacements[i];
                asserting(_liszt.contains(expected));
                assertBand(expected,actual);
            }

            return true;
        });
    }
}