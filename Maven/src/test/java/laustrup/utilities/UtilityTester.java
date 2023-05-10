package laustrup.utilities;

import laustrup.models.Model;
import laustrup.models.users.sub_users.bands.Artist;
import laustrup.quality_assurance.*;
import laustrup.quality_assurance.inheritances.items.TestItems;
import laustrup.utilities.collections.lists.Liszt;
import laustrup.utilities.collections.sets.Seszt;

import org.junit.jupiter.api.AfterEach;

/**
 * A testing type class, that extends Tester class with both types as Object.
 * Contains collections, that can be used for test purposes.
 */
public abstract class UtilityTester extends Tester<Object> {

    /**
     * A Seszt that can be used for testing purposes,
     * Will be reset after each test.
     */
    protected Seszt<Artist> _seszt;

    /**
     * A Liszt that can be used for testing purposes,
     * Will be reset after each test.
     */
    protected Liszt<Artist> _liszt;

    /** An Artist that can be used for test purposes. */
    protected Artist _artist;

    /** Artists that can be used for test purposes. */
    protected Artist[] _artists;

    /**
     * Default constructor.
     * Sets up Artists once.
     */
    protected UtilityTester() {
        _items = new TestItems();
        setupArtists();
    }

    /** Will set up both artist and artists from items. */
    private void setupArtists() {
        Artist[] artists = new Artist[4];

        for (int i = 0; i < artists.length; i++) {
            Artist artist;
            do {
                artist = _items.get_artists()[_random.nextInt(_items.get_artistAmount())];
            } while (isIncluded(artists,artist));

            artists[i] = artist;
        }

        _adding = artists[0];
        _artist = (Artist) _adding;
        _addings = new Object[]{artists[1], artists[2], artists[3]};
        _artists = artists;
    }

    /**
     * Will check if an object is included in an array of objects.
     * @param objects The objects that will be iterated through.
     * @param object The object that might or might not exist in objects.
     * @return True if object has the same toString() as an index of objects.
     */
    private boolean isIncluded(Object[] objects, Object object) {
        for (Object index : objects)
            if (index != null && index.toString().equals(object))
                return true;

        return false;
    }

    @AfterEach
    void afterEach() {
        _seszt = new Seszt<>();
        _liszt = new Liszt<>();
    }
}
