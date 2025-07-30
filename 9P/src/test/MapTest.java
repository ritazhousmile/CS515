import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

/**
 * New tests for Java set using Java iterator and set methods.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class MapTest {

    @Test
    void testMap00() throws IOException {

        StringBuilder actual = new StringBuilder();

        Map<String, String> m1 = new Map<>();

        actual.append("\n\nMap test 1\n\n");

        assertTrue( m1.insert("cute", "boy"));
        assertTrue( m1.insert("cool", "breeze"));
        assertTrue( m1.insert("warm", "fire"));
        assertTrue( m1.insert("red", "rose"));
        assertFalse( m1.insert("cool", "ice"));

        actual.append(m1.toString() + "\n");

        Iterator<Entry<String, String>> iter = m1.iterator();

        while (iter.hasNext()){
            actual.append(iter.next().getKey() + " ");
        }

        actual.append("\n\nMap test 2\n\n");

        Map<Integer, Integer> m2 = new Map<>();

        m2.insert(29, 1);
        m2.insert(34, 1);
        m2.insert(39, 1);
        m2.insert(23, 1);
        m2.insert(87, 1);
        m2.insert(45, 1);
        m2.insert(83, 1);
        m2.insert(22, 1);
        m2.insert(12, 1);
        m2.insert(57, 1);
        m2.insert(21, 1);
        m2.insert(30, 1);
        m2.insert(2, 1);
        m2.insert(26, 1);
        m2.insert(5, 1);
        m2.insert(11, 1);
        m2.insert(32, 1);
        m2.insert(86, 1);

        actual.append(m2.toString() +  "\n\n\n\n\n");

        m2.replace(29, 2);
        m2.replace(34, 2);
        m2.replace(39, 2);
        m2.replace(23, 2);
        m2.replace(87, 2);
        m2.replace(45, 2);
        m2.replace(83, 2);
        m2.replace(22, 2);
        m2.replace(12, 2);
        m2.replace(57, 2);
        m2.replace(21, 2);
        m2.replace(30, 2);
        m2.replace(2, 2);
        m2.replace(26, 2);
        m2.replace(5, 2);
        m2.replace(11, 2);
        m2.replace(32, 2);
        m2.replace(86, 2);

        actual.append(m2.toString() + "\n\n");

        Iterator<Entry<Integer, Integer>> iter2 = m2.iterator();


        while (iter2.hasNext()){
            actual.append(iter2.next().getKey() + " ");
        }
        actual.append("\n");

        System.out.println(actual.toString());

        InputStream is = MapTest.class.getResourceAsStream("/out0");
        String expected = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual.toString());
    }

    @Test
    void testMap01() throws IOException {

        StringBuilder actual = new StringBuilder();

        Map<Integer, Integer> m1 = new Map<>();

        assertTrue(m1.insert(12, 1));
        assertTrue(m1.insert(11, 2));
        assertTrue(m1.insert(13, 1));

        actual.append(m1.toString());

        assertTrue( m1.insert(10, 2));
        assertTrue( m1.insert(9, 1));
        assertTrue( m1.insert(8, 2));
        assertTrue( m1.insert(7, 1));
        assertTrue( m1.insert(6, 2));
        assertTrue( m1.insert(14, 1));
        assertTrue( m1.insert(15, 2));
        assertTrue( m1.insert(17, 1));
        assertTrue( m1.insert(20, 2));

        assertFalse( m1.insert(14, 1));
        assertFalse( m1.insert(15, 2));
        assertFalse( m1.insert(17, 1));
        assertFalse( m1.insert(20, 2));

        actual.append(m1.toString());
        actual.append("--------------------------------\n");

        Map<Integer, Character> m2 = new Map<>();

        assertTrue( m2.insert(14, '<'));
        assertTrue( m2.insert(15, '<'));
        assertTrue( m2.insert(17, '<'));
        assertTrue( m2.insert(9, '<'));
        assertTrue( m2.insert(8, '<'));
        assertTrue( m2.insert(7, '<'));
        assertTrue( m2.insert(6, '<'));
        assertTrue( m2.insert(20, '<'));
        assertTrue( m2.insert(10, '<'));
        assertTrue( m2.insert(12, '<'));
        assertTrue( m2.insert(11, '<'));
        assertTrue( m2.insert(13, '<'));

        assertFalse( m2.insert(7, '<'));
        assertFalse( m2.insert(6, '<'));
        assertFalse( m2.insert(20, '<'));
        assertFalse( m2.insert(10, '<'));

        actual.append(m2.toString());
        actual.append("--------------------------------\n");


        Map<Integer, Character> m3 = new Map<>();

        assertTrue( m3.insert(1, 'A'));
        assertTrue( m3.insert(2, 'A'));
        assertTrue( m3.insert(3, 'A'));
        assertTrue( m3.insert(4, 'A'));
        assertTrue( m3.insert(5, 'A'));
        assertTrue( m3.insert(6, 'A'));
        assertTrue( m3.insert(7, 'A'));
        assertTrue( m3.insert(8, 'A'));
        assertTrue( m3.insert(9, 'A'));
        assertTrue( m3.insert(10, 'A'));

        assertFalse( m3.insert(7, Character.forDigit(7, 10)));
        assertFalse( m3.insert(6, Character.forDigit(7, 10)));
        assertFalse( m3.insert(3, Character.forDigit(7, 10)));
        assertFalse( m3.insert(4, Character.forDigit(7, 10)));

        actual.append(m3.toString());

        System.out.println(actual.toString());

        InputStream is = MapTest.class.getResourceAsStream("/out1");
        String expected = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual.toString());

    }

    @Test
    void testMap02() throws IOException {

        StringBuilder actual = new StringBuilder();

        Map<Integer, Integer> m1 = new Map<>();


        assertTrue( m1.insert(12, 1));
        assertTrue( m1.insert(11, 2));
        assertTrue( m1.insert(13, 1));
        assertTrue( m1.insert(10, 2));
        assertTrue( m1.insert(9, 1));
        assertTrue( m1.insert(8, 2));
        assertTrue( m1.insert(7, 1));
        assertTrue( m1.insert(6, 2));
        assertTrue( m1.insert(14, 1));
        assertTrue( m1.insert(15, 2));
        assertTrue( m1.insert(17, 1));
        assertTrue( m1.insert(20, 2));
        assertTrue( m1.insert(44, 1));
        assertTrue( m1.insert(54, 2));
        assertTrue( m1.insert(55, 1));
        assertTrue( m1.insert(25, 2));
        assertTrue( m1.insert(18, 1));
        assertTrue( m1.insert(19, 2));
        assertTrue( m1.insert(97, 1));
        assertTrue( m1.insert(21, 2));

        Iterator<Entry<Integer, Integer>> it = m1.iterator();

        while(it.hasNext()) {
            actual.append(it.next().getKey()).append(" ");
        }
        actual.append("\n");

        assertThrows(NoSuchElementException.class, () -> {

                Iterator<Entry<Integer, Integer>> it1 = m1.iterator();
                for (int i=0; i<19; i++)
                    it1.next();
                assertEquals(97, it1.next().getKey());

                it1.next();
                it1.next(); });

        System.out.println(actual.toString());

        InputStream is = MapTest.class.getResourceAsStream("/out2");
        String expected = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual.toString());
    }

    @Test
    void testMap03() throws IOException {

        StringBuilder actual = new StringBuilder();

        Map<Integer, Integer> m1 = new Map<>();


        assertEquals(0, m1.size());
        assertNull(m1.get(1));

        actual.append("size(should be 0): " + m1.size() + "\n");

        m1.insert(21,1);
        m1.insert(4,1);
        m1.insert(1,1);
        m1.insert(2,1);
        m1.insert(35,1);
        actual.append("size(should be 5): " + m1.size() + "\n");

        m1.insert(3,1);
        m1.insert(28,1);
        m1.insert(31,1);
        m1.insert(12,1);
        m1.insert(33,1);
        actual.append("size(should be 10): " + m1.size() + "\n");

        m1.insert(45,1);
        m1.insert(49,1);
        m1.insert(17,1);
        m1.insert(39,1);
        m1.insert(44,1);
        actual.append("size(should be 15): " + m1.size() + "\n");

        m1.insert(4,1);
        m1.insert(28,1);

        actual.append(m1.toString());
        actual.append("size(should be 15): " + m1.size() + "\n");

        assertEquals(15, m1.size());

        // elements in the set
        assertNotNull(m1.get(17));
        assertNotNull(m1.get(12));
        assertNotNull(m1.get(4));
        assertNotNull(m1.get(1));
        assertNotNull(m1.get(49));
        assertNotNull(m1.get(21));
        assertNotNull(m1.get(28));

        // elements not in the set
        assertNull(m1.get(13));
        assertNull(m1.get(18));
        assertNull(m1.get(0));
        assertNull(m1.get(100));
        assertNull(m1.get(42));
        assertNull(m1.get(50));
        assertNull(m1.get(15));
        assertNull(m1.get(-10));

        Iterator<Entry<Integer, Integer>> it = m1.iterator();

        while(it.hasNext()) {
            actual.append(it.next().getKey()).append(" ");
        }
        actual.append("\n");

        System.out.println(actual.toString());

        InputStream is = MapTest.class.getResourceAsStream("/out3");
        String expected = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual.toString());
    }

    @Test
    void testMap04() throws IOException {

        StringBuilder actual = new StringBuilder();

        Map<Integer, Integer> m1 = new Map<>();

        assertEquals(0, m1.size());
        assertNull(m1.get(1));

        m1.insert(1, 1);
        m1.insert(2, 1);
        m1.insert(35, 1);
        m1.insert(39, 1);
        m1.insert(44, 1);
        m1.insert(4, 1);
        m1.insert(28, 1);
        m1.insert(3, 1);
        m1.insert(21, 1);
        m1.insert(31, 1);
        m1.insert(12, 1);
        m1.insert(33, 1);
        m1.insert(45, 1);
        m1.insert(49, 1);
        m1.insert(17, 1);

        actual.append(m1.toString());

        assertEquals(15, m1.size());

        assertEquals(1, m1.get(17));
        assertEquals(1, m1.get(12));
        assertEquals(1, m1.get(4));
        assertEquals(1, m1.get(1));
        assertEquals(1, m1.get(49));

        assertEquals(1, m1.get(21));
        assertEquals(1, m1.get(28));

        // elements not in the set
        assertNull(m1.get(13));
        assertNull(m1.get(18));
        assertNull(m1.get(0));
        assertNull(m1.get(100));
        assertNull(m1.get(42));
        assertNull(m1.get(50));
        assertNull(m1.get(15));
        assertNull(m1.get(10));

        assertEquals(1, m1.replace(44, 2));
        assertEquals(1, m1.replace(4, 2));
        assertEquals(1, m1.replace(28, 2));
        assertEquals(1, m1.replace(3, 2));
        assertEquals(1, m1.replace(39, 2));
        assertEquals(1, m1.replace(33, 2));
        assertEquals(1, m1.replace(45, 2));
        assertEquals(1, m1.replace(49, 2));
        assertEquals(1, m1.replace(17, 2));

        // elements in the set
        assertEquals(2, m1.get(44));
        assertEquals(2, m1.get(4));
        assertEquals(2, m1.get(28));
        assertEquals(2, m1.get(3));
        assertEquals(2, m1.get(39));
        assertEquals(2, m1.get(33));
        assertEquals(2, m1.get(45));
        assertEquals(2, m1.get(49));
        assertEquals(2, m1.get(17));


        Iterator<Entry<Integer, Integer>> it = m1.iterator();

        while(it.hasNext()) {
            actual.append(it.next().getKey()).append(" ");
        }
        actual.append("\n");

        System.out.println(actual.toString());

        InputStream is = MapTest.class.getResourceAsStream("/out4");
        String expected = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual.toString());
    }

    @Test
    void testMap05() throws IOException {

        StringBuilder actual = new StringBuilder();

        Map<Integer, Integer> m1 = new Map<>();

        m1.insert(45,1);
        actual.append("----------insert 45---------\n");
        actual.append(m1.toString());


        m1.insert(21,1);
        actual.append("----------insert 21---------\n");
        actual.append(m1.toString());


        m1.insert(4,1);
        actual.append("----------insert 4---------\n");
        actual.append(m1.toString());

        m1.insert(61,1);
        actual.append("----------insert 61---------\n");
        actual.append(m1.toString());


        m1.insert(71,1);
        actual.append("----------insert 71---------\n");
        actual.append(m1.toString());

        m1.insert(46,1);
        actual.append("----------insert 46---------\n");
        actual.append(m1.toString());

        m1.insert(2,1);
        actual.append("----------insert 2---------\n");
        actual.append(m1.toString());

        m1.insert(35,1);
        actual.append("----------insert 35---------\n");
        actual.append(m1.toString());

        m1.insert(36,1);
        actual.append("----------insert 36---------\n");
        actual.append(m1.toString());

        m1.insert(3,1);
        actual.append("----------insert 3---------\n");
        actual.append(m1.toString());

        m1.insert(8,1);
        actual.append("----------insert 8---------\n");
        actual.append(m1.toString());

        m1.insert(31,1);
        actual.append("----------insert 31---------\n");
        actual.append(m1.toString());

        m1.insert(12,1);
        actual.append("----------insert 12---------\n");
        actual.append(m1.toString());

        m1.insert(44,2);
        actual.append("----------insert 44---------\n");
        actual.append(m1.toString());

        m1.insert(30,2);
        actual.append("----------insert 30---------\n");
        actual.append(m1.toString());

        m1.insert(16,2);
        actual.append("----------insert 16---------\n");
        actual.append(m1.toString());

        System.out.println(actual.toString());

        InputStream is = MapTest.class.getResourceAsStream("/out5");
        String expected = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual.toString());
    }

    @Test
    void testMap06() throws IOException {

        StringBuilder actual = new StringBuilder();

        Map<Integer, Integer> m1 = new Map<>();


        m1.insert(40,1);
        m1.insert(12,1);
        m1.insert(6,1);
        m1.insert(78,1);
        m1.insert(45,1);
        m1.insert(33,1);
        m1.insert(21,1);
        m1.insert(4,1);
        m1.insert(29,1);
        m1.insert(9,1);
        m1.insert(5,1);
        m1.insert(42,1);
        m1.insert(23,1);
        m1.insert(11,1);
        m1.insert(64,1);
        m1.insert(46,1);
        m1.insert(20,1);
        m1.insert(13,1);
        m1.insert(28,1);
        m1.insert(54,1);
        m1.insert(77,1);
        m1.insert(85,1);
        m1.insert(14,1);
        m1.insert(17,1);
        m1.insert(15,1);
        m1.insert(9,1);
        m1.insert(56,1);
        m1.insert(47,1);
        m1.insert(83,1);
        m1.insert(31,1);
        m1.insert(59,1);
        m1.insert(66,1);
        m1.insert(61,1);
        m1.insert(63,1);
        m1.insert(23,1);
        m1.insert(69,1);
        m1.insert(70,1);
        m1.insert(89,1);
        m1.insert(91,1);
        m1.insert(67,1);
        m1.insert(75,1);

        assertEquals(39, m1.size());
        actual.append(m1.toString());

        System.out.println(actual.toString());

        InputStream is = MapTest.class.getResourceAsStream("/out6");
        String expected = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual.toString());
    }

    @Test
    void testMap07() throws IOException {

        Map<Integer, Integer> m1 = new Map<>();

        for(int i=1; i<=100000; i++)
            m1.insert(i,0);

        Iterator<Entry<Integer, Integer>> it = m1.iterator();
        int key = 1;
        while(it.hasNext()) {
            assertEquals(key, it.next().getKey());
            key++;
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void testMap08() throws IOException {

        StringBuilder actual = new StringBuilder();

        Map<String, String> m1 = new Map<>();
        Iterator<Entry<String, String>> it;

        assertTrue( m1.insert("a", "boy"));
        assertTrue( m1.insert("k", "kite"));
        assertTrue( m1.insert("z", "zoo"));
        assertTrue( m1.insert("b", "breeze"));
        assertTrue( m1.insert("r", "fire"));
        assertTrue( m1.insert("d", "rose"));
        assertTrue( m1.insert("c", "ice"));
        assertFalse( m1.insert("c", "car"));

        Map<String, String> m2 = new Map<>();

        actual.append(m1.toString());

        actual.append("\n++++++++m2++++++++++++\n");
        it = m2.iterator();

        while(it.hasNext()) {
            actual.append(it.next().getKey()).append(" ");
        }
        actual.append("\n");

        m2 = (Map<String, String>)m1.clone();
        assertEquals(7, m1.size());
        assertEquals(7, m2.size());

        actual.append("++++++++m2 after assignment++++++++++++\n");
        it = m2.iterator();

        while(it.hasNext()) {
            actual.append(it.next().getKey()).append(" ");
        }
        actual.append("\n");

        actual.append("++++++++m2 after modificiation++++++++++++\n");
        it = m2.iterator();

        while(it.hasNext()) {
            actual.append(it.next().getKey()).append(" ");
        }
        actual.append("\n");

        m2.insert("haha", "me here");
        actual.append("++++++++m2 after modificiation++++++++++++\n");
        it = m2.iterator();

        while(it.hasNext()) {
            actual.append(it.next().getKey()).append(" ");
        }
        actual.append("\n");


        m2 = (Map<String, String>)m1.clone();


        actual.append("++++++++m2 after modificiation again++++++++++++\n");
        it = m2.iterator();

        while(it.hasNext()) {
            actual.append(it.next().getKey()).append(" ");
        }
        actual.append("\n");

        actual.append("++++++++m1 after modificiation again++++++++++++\n");
        it = m1.iterator();

        while(it.hasNext()) {
            actual.append(it.next().getKey()).append(" ");
        }
        actual.append("\n");

        System.out.println(actual.toString());

        InputStream is = MapTest.class.getResourceAsStream("/out8");
        String expected = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual.toString());
    }

    @Test
    void testMap09() throws IOException {

        StringBuilder actual = new StringBuilder();

        Map<String, String> m1 = new Map<>();

        assertTrue( m1.insert("cute", "boy"));
        assertTrue( m1.insert("cool", "breeze"));
        assertTrue( m1.insert("warm", "fire"));
        assertTrue( m1.insert("red", "rose"));
        assertFalse( m1.insert("cool", "ice"));

        m1.forEach((k,v) -> {
                actual.append(String.format("key=%s, value=%s%n",k ,v));
            });

        actual.append("\n");

        Map<Integer, Integer> m2 = new Map<>();

        m2.insert(29, 1);
        m2.insert(34, 1);
        m2.insert(39, 1);
        m2.insert(23, 1);
        m2.insert(87, 1);
        m2.insert(45, 1);
        m2.insert(83, 1);
        m2.insert(22, 1);
        m2.insert(12, 1);
        m2.insert(57, 1);
        m2.insert(21, 1);
        m2.insert(30, 1);
        m2.insert(2, 1);
        m2.insert(26, 1);
        m2.insert(5, 1);
        m2.insert(11, 1);
        m2.insert(32, 1);
        m2.insert(86, 1);

        m2.forEach((k,v) -> {
                actual.append(String.format("key=%s, value=%s%n",k ,v));
            });

        Map<String, String> m3 = new Map<>();

        m3.forEach((k,v) -> {
                actual.append(String.format("key=%s, value=%s%n",k ,v));
            });

        var wrapper = new Object(){ int keylengths=0; int vallengths=0;};
        m1.forEach((k,v) -> {
                wrapper.keylengths += k.length();
                wrapper.vallengths += v.length();
            });

        assertEquals(15, wrapper.keylengths);
        assertEquals(17, wrapper.vallengths);

        System.out.println(actual.toString());

        InputStream is = MapTest.class.getResourceAsStream("/out9");
        String expected = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual.toString());
    }

    @Test
    void testMap10() throws IOException {

        StringBuilder actual = new StringBuilder();

        Map<String, Integer> m1 = new Map<>();

        m1.insert("0", 0);
        m1.insert("1", 2);
        m1.insert("2", 2);
        m1.insert("3", 3);
        m1.insert("4", 4);
        m1.insert("5", 5);
        m1.insert("6", 6);
        m1.insert("7", 7);
        m1.insert("8", 8);
        m1.insert("9", 9);
        m1.insert("10", 10);

        actual.append("++++++++ m1 before operation ++++++++\n");
        actual.append(m1.toString());

        actual.append("++++++++ m1 after operation ++++++++\n");
        m1.replaceAll((k,v) -> v * v);

        actual.append(m1.toString());

        actual.append("++++++++ m1 after another operation ++++++++\n");

        m1.replaceAll((k, v) -> {
                if( 25 <= v && v <= 75) {
                    return v;
                } else {
                    return null;
                }});

        actual.append(m1.toString());


        Map<Integer, String> m2 = new Map<>();

        m2.insert(1, "one");
        m2.insert(2, "two");
        m2.insert(3, "three");
        m2.insert(4, "four");
        m2.insert(5, "five");
        m2.insert(6, "six");
        m2.insert(7, "seven");
        m2.insert(8, "eight");
        m2.insert(9, "nine");
        m2.insert(10, "ten");

        actual.append("++++++++ m2 before operation ++++++++\n");
        actual.append(m2.toString());

        actual.append("++++++++ m2 after operation ++++++++\n");
        m2.replaceAll((k,v) -> v.substring(0,1).toUpperCase() + v.substring(1));

        actual.append(m2.toString());

        System.out.println(actual.toString());

        InputStream is = MapTest.class.getResourceAsStream("/out10");
        String expected = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual.toString());
    }
}
