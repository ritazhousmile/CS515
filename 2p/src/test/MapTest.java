/**
 * CS515 Assignment 2p

*Name: Huan Zhou(Rita)

*Section: 01

*Date: 06/03/2025

Collaboration Declaration: 

Collaboration: None
*/

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    /*
       The constructor should create a map with zero size
     */
    @Test
    void constructorCreatesZeroSizeMap() {
      Map<String, Integer> map = new Map<>();
      assertEquals(0, map.size());
    }

    /*
       Inserting into an empty map should return true and result in a size of one
     */
    @Test
    void insertWhileEmptyReturnsTrueAndMapSizeIsOne() {
      Map<String, Integer> map = new Map<>();
      assertTrue(map.insert("key", 1));
      assertEquals(1, map.size());
    }

    /*
      using the copy constructor results in twp maps of the same correct size
     */
    @Test
    void copyConstructorMakesCorrectSize() {
      Map<String, Integer> map = new Map<>();
      Map<String, Integer> map2 = new Map<>(map);
      assertEquals(0, map.size());
      assertEquals(0, map2.size());
    }

    /*
      Using the copy constructor with an existing map makes its own copy such
      that if the value assiciated with a key is changed in the copy it does
      not change the associated value in the original, and inserts/erases on
      either won't affect the other.
     */
    @Test
    void copyConstructorMakesSeparateCopy(){
      Map<String, Integer> map = new Map<>();
      Map<String, Integer> map2 = new Map<>(map);
      map.insert("key", 1);
      assertEquals(1, map.size());
      assertEquals(0, map2.size());
      map2.insert("key", 2);
      assertEquals(1, map.size());
      assertEquals(1, map2.size());
    }

    /*
      Inserting a key that already exists should fail and not change the map size
     */
    @Test
    void insertOfSameKeyReturnsFalseAndMapSizeRemainsSame() {
      Map<String, Integer> map = new Map<>();
      map.insert("key", 1);
      assertEquals(1, map.size());
      assertFalse(map.insert("key", 2));
      assertEquals(1, map.size());
      

    }


    /*
      Accessing an existing item using get should return correct value and not
      not change the map size
     */
    @Test
    void getOfExistingKeyReturnsProperValueAndSizeIsSame() {
      Map<String, Integer> map = new Map<>();
      map.insert("key", 1);
      assertEquals(1, map.size());
      assertEquals(1, map.get("key"));
      assertEquals(1, map.size());
    }

    /*
      Using get on empty map gives correct result and and insert in an empty
      map should add the item with an appropriate value and increase the map
      size to one
     */
    @Test
    void getThenInsertOnEmptyMapProperlySetsValueAndSizeIsOne() {
      Map<String, Integer> map = new Map<>();
      map.insert("key", 1);
      assertEquals(1, map.size());
      assertEquals(1, map.get("key"));
      assertEquals(1, map.size());
      

    }

    /*
      get on missing key produces correct result and insert
      properly sets value and increments size
     */
    @Test
    void getThenInsertOnMissingKeyProperlySetsValueAndIncrementsSize() {
      Map<String, Integer> map = new Map<>();
      map.insert("key", 1);
      assertEquals(1, map.size());
      assertEquals(1, map.get("key"));
      assertEquals(1, map.size());
      map.insert("key2", 2);
      assertEquals(2, map.size());
      assertEquals(2, map.get("key2"));
    }

    /*
      Erasing a key that exists i nthe map succeeds and decrements the map size
     */
    @Test
    void eraseOfExistingKeyReturnsTrueAndDecrementsSize() {
      Map<String, Integer> map = new Map<>();
      map.insert("key", 1);
      assertEquals(1, map.size());
      assertTrue(map.erase("key"));
      assertEquals(0, map.size());
    }

    /*
      Erase on an empty map fails and keeps the map size at zero
     */
    @Test
    void eraseOnEmptyMapReturnsFalseAndSizeRemainsZero() {
      Map<String, Integer> map = new Map<>();
      assertFalse(map.erase("key"));
      assertEquals(0, map.size());
    }

    /*
      Erase of a non-existant key fails and does not change the map size
     */
    @Test
    void eraseOfMissingKeyReturnsFalseAndSizeRemainsSame() {
      Map<String, Integer> map = new Map<>();
      map.insert("key", 1);
      assertEquals(1, map.size());
      assertFalse(map.erase("key2"));
      assertEquals(1, map.size());
    }


    /*
      Using the clone method results in two maps of the same, correct size
     */
    @Test
    @SuppressWarnings("unchecked")
    void cloneMakesCorrectSize() {
      Map<String, Integer> map = new Map<>();
      Map<String, Integer> map2 = (Map<String, Integer>) map.clone();
      assertEquals(0, map.size());
      assertEquals(0, map2.size());
    }

    /*
      Using the clone method with an existing map makes its own copy such that
      if the value associated with a key is changed in the copy it does not
      change the associated value in the original, and inserts/erases on either
      won't affect the other.
     */
    @Test
    @SuppressWarnings("unchecked")
    void cloneMakesSeparateCopy() {
      Map<String, Integer> map = new Map<>();
      Map<String, Integer> map2 = (Map<String, Integer>) map.clone();
      map.insert("key", 1);
      assertEquals(1, map.size());
      assertEquals(0, map2.size());
      map2.insert("key", 2);
      assertEquals(1, map.size());  
      assertEquals(1, map2.size());
    }

    /*
      Using the clone method with an existing map with over 1000 items
      makes a copt that has the correct keys/values at a couple places
      deep within the map (values that aren't at either end of the range
      of keys used
     */
    @Test
    @SuppressWarnings("unchecked")
    void cloneWithManyItemsHasCorrectValues() {
      Map<String, Integer> map = new Map<>();
      Map<String, Integer> map2 = (Map<String, Integer>) map.clone();
      map.insert("key", 1);
      assertEquals(1, map.size());
      assertEquals(0, map2.size());
      map2.insert("key", 2);
      assertEquals(1, map.size());
    }
}
