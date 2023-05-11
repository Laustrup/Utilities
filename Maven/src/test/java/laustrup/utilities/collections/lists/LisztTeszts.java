package laustrup.utilities.collections.lists;

import laustrup.models.users.User;
import laustrup.models.users.sub_users.bands.Artist;
import laustrup.models.users.sub_users.bands.Band;
import laustrup.quality_assurance.*;
import laustrup.utilities.console.Printer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LisztTeszts extends Tester<Object> {

    /** The Liszt that will be used in the tests for testing. */
    private Liszt<Object> _liszt;

    @ParameterizedTest
    @CsvSource(value = {"true", "false"}, delimiter = '|')
    void constructorTest(boolean isEmptyDataTemplate) {
        test(() -> {
            arrange();

            if (isEmptyDataTemplate) {
                act(() -> _liszt = new Liszt<>());

                asserting(_liszt.isEmpty());
            }
            else {
                act(() -> _liszt = new Liszt<>(new Object[]{true,false}));

                asserting(true, (_liszt.Get(1)));
                asserting(false, (_liszt.Get(2)));
            }
        });
    }

    @Test
    void canAddSingleElement() {
        test(() -> {
            Object expected = arrange(() -> {
                _liszt = new Liszt<>();
                return 1;
            });

            act(() -> _liszt.add(expected));

            asserting(expected, _liszt.Get(1));
        });
    }

    @ParameterizedTest
    @CsvSource(value = {"true","false"})
    void canAddAnArray(boolean constructorWithArgument) {
        test(() -> {
            arrange(() -> {
                _liszt = constructorWithArgument ? new Liszt<>(_items.get_bands()) : new Liszt<>();
                return _liszt;
            });

            act(() -> {
                if (!((Boolean) constructorWithArgument))
                    _liszt.add(_items.get_bands());
                return _liszt;
            });

            for (Band band : _items.get_bands())
                asserting(band, _liszt.Get(band.toString()));

            for (int i = 1; i <= _items.get_bands().length; i++)
                asserting(_items.get_bands()[i-1], _liszt.Get(i));
        });
    }

    @Test
    void canRemove() {
        test(() -> {
            User expected = (User) arrange(() -> {
                User user = _items.generateUser();
                _liszt = new Liszt<>(new Object[]{user});
                return user;
            });

            act(() -> _liszt.remove(expected));

            asserting(_liszt.isEmpty());
        });
    }

    @Test
    void canRemoveMultiple() {
        test(() -> {
            Band[] expectations = (Band[]) arrange(() -> {
                Band[] bands = _items.get_bands();
                _liszt = new Liszt<>(bands);
                return bands;
            });

            act(() -> _liszt.remove(expectations));

            asserting(_liszt.isEmpty());
        });
    }

    @Test
    void canSet() {
        Artist index = (Artist) _addings[0],
            expect = (Artist) _addings[1];

        test(() -> {
            Artist expected = (Artist) arrange(() -> {
                _liszt.add(index);
                return expect;
            });

            asserting(expected,act(() -> _liszt.set(index, expected)));
        });

        test(() -> {
            Artist expected = (Artist) arrange(() -> {
                _liszt.add(index);
                return expect;
            });

            asserting(expected,act(() -> _liszt.set(1, expected)));
        });
    }

    @ParameterizedTest
    @CsvSource(value = {"0"+_divider+"0","1"+_divider+"0","0"+_divider+"1","3"+_divider+"0","0"+_divider+"3",
                        "1"+_divider+"1","3"+_divider+"1","1"+_divider+"3","3"+_divider+"3"}, delimiter = _delimiter)
    void canSetMultiple(int elementsSize, int replacementsSize) {
        Object[] elements = new Object[elementsSize],
                 replacements = new Object[replacementsSize];
        _liszt = new Liszt<>();

        test(() -> {
            arrange(() -> {
                for (int i = 0; i < elementsSize; i++)
                    elements[i] = _items.get_bands()[_random.nextInt(_items.get_bandAmount())];
                for (int i = 0; i < replacementsSize; i++)
                    replacements[i] = _items.get_bands()[_random.nextInt(_items.get_bandAmount())];

                _liszt.add(elements);
                return Printer.get_instance().toString(_liszt.get_data());
            });

            act(() -> _liszt.set(elements, replacements));

            for (int i = 0; i < _liszt.size(); i++) {
                Band actual = (Band) _liszt.get(i),
                     expected = (Band) replacements[i];
                asserting(_liszt.contains(expected));
                assertBand(expected,actual);
            }
        });
    }
}