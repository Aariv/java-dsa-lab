package com.ariv.dsa.datastructure.linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DoublyLinkedListTest {

    @Test
    public void shouldAddFirst() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        // Add assertions to verify the list structure and size
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(3, list.getFirst());
        Assertions.assertEquals(1, list.getLast());
    }

    @Test
    public void shouldAddLast() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(40);
        // Add assertions to verify the list structure and size
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals(1, list.getFirst());
        Assertions.assertEquals(40, list.getLast());
    }

    @Test
    public void shouldRemoveFirst() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(40);
        list.addLast(50);

        // Add assertions to verify the list structure and size
        Assertions.assertEquals(5, list.size());
        Assertions.assertEquals(1, list.getFirst());
        Assertions.assertEquals(1, list.removeFirst());
    }

    @Test
    public void shouldRemoveLast() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(40);
        list.addLast(50);

        // Add assertions to verify the list structure and size
        Assertions.assertEquals(5, list.size());
        Assertions.assertEquals(1, list.getFirst());
        Assertions.assertEquals(50, list.removeLast());
    }

    @Test
    public void shouldContainValue() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(40);
        list.addLast(50);

        // Add assertions to verify the list structure and size
        Assertions.assertEquals(5, list.size());
        Assertions.assertTrue(list.contains(1));
    }

    @Test
    public void shouldReturnFirstElement() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(40);
        list.addLast(50);

        // Add assertions to verify the list structure and size
        Assertions.assertEquals(5, list.size());
        Assertions.assertEquals(1, list.getFirst());
    }

    @Test
    public void shouldReturnLastElement() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(40);
        list.addLast(50);

        // Add assertions to verify the list structure and size
        Assertions.assertEquals(5, list.size());
        Assertions.assertEquals(50, list.getLast());
    }

    @Test
    void shouldInsertAtIndex() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(10);
        list.addLast(30);

        list.add(1, 20);

        assertThat(list.get(1)).isEqualTo(20);
    }

    @Test
    void shouldRemoveAtIndex() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        Integer removed = list.remove(1);

        assertThat(removed).isEqualTo(20);

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void shouldReverseList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        list.reverse();

        assertThat(list.get(0)).isEqualTo(30);

        assertThat(list.get(1)).isEqualTo(20);

        assertThat(list.get(2)).isEqualTo(10);
    }
}
