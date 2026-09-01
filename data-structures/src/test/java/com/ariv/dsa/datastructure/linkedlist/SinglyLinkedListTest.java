package com.ariv.dsa.datastructure.linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest {

    @Test
    public void shouldAddFirst() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
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
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
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
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
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
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
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
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
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
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
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
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(40);
        list.addLast(50);

        // Add assertions to verify the list structure and size
        Assertions.assertEquals(5, list.size());
        Assertions.assertEquals(50, list.getLast());
    }
}
