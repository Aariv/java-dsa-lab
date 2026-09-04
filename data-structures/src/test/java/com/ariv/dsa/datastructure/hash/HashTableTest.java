package com.ariv.dsa.datastructure.hash;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HashTableTest {

    @Test
    void shouldInsertValue() {

        HashTable<String,Integer> table = new HashTable<>();

        table.put("A", 10);

        assertThat(table.get("A")).isEqualTo(10);
    }

    @Test
    void shouldUpdateValue() {

        HashTable<String,Integer> table = new HashTable<>();

        table.put("A", 10);

        table.put("A", 20);

        assertThat(table.get("A")).isEqualTo(20);
    }

    @Test
    void shouldReturnNullForMissingKey() {

        HashTable<String,Integer> table = new HashTable<>();

        assertThat(table.get("A")).isNull();
    }

    @Test
    void shouldContainKey() {

        HashTable<String,Integer> table = new HashTable<>();

        table.put("A", 10);

        assertThat(table.get("A")).isEqualTo(10);
    }

    @Test
    void shouldHandleCollisions() {

        HashTable<TestKey,Integer> table = new HashTable<>();

        table.put( new TestKey("A"), 10);

        table.put(new TestKey("B"), 20);

        assertThat(table.get(new TestKey("A"))).isEqualTo(10);

        assertThat(table.get(new TestKey("B"))).isEqualTo(20);
    }

    static class TestKey {

        private final String value;

        TestKey(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {

            if(this == obj) {
                return true;
            }

            if(!(obj instanceof TestKey other)) {
                return false;
            }

            return Objects.equals(value,other.value);
        }
    }

    @Test
    void shouldRemoveEntryFromMiddleOfChain() {

        HashTable<TestKey,Integer> table = new HashTable<>();

        table.put( new TestKey("A"), 10);

        table.put(new TestKey("B"), 20);

        table.put(new TestKey("C"), 30);

        assertThat(table.get(new TestKey("B"))).isEqualTo(20);

        table.remove(new TestKey("B"));

        assertThat(table.get(new TestKey("B"))).isNull();

        assertThat(table.get(new TestKey("A"))).isEqualTo(10);

        assertThat(table.get(new TestKey("C"))).isEqualTo(30);
    }

    @Test
    void shouldUpdateExistingKey() {

        HashTable<TestKey,Integer> table = new HashTable<>();

        table.put( new TestKey("A"), 10);

        table.put(new TestKey("B"), 20);

        table.put(new TestKey("C"), 30);

        assertThat(table.get(new TestKey("B"))).isEqualTo(20);

        table.put(new TestKey("B"), 25);

        assertThat(table.get(new TestKey("B"))).isEqualTo(25);
    }

    @Test
    void shouldResize() {

        HashTable<Integer,Integer> table = new HashTable<>();

        for(int i = 0; i < 13; i++) {
            table.put(i, i);
        }

        assertThat(table.capacity()).isEqualTo(32);
    }

    @Test
    void shouldPreserveEntriesAfterResize() {

        HashTable<Integer,Integer> table = new HashTable<>();

        for(int i = 0; i < 100; i++) {
            table.put(i, i * 10);
        }

        for(int i = 0; i < 100; i++) {
            assertThat(
                    table.get(i)
            ).isEqualTo(i * 10);
        }
    }

}
