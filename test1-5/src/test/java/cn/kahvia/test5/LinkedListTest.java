package cn.kahvia.test5;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LinkedListTest {

    //删除不存在的null对象
    @Test
    void testRemoveNullObjectNoExits(){
        LinkedList<String> linkedList=new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        assertFalse(linkedList.remove(null));
    }

    //删除存在的null对象
    @Test
    void testRemoveNullObjectExits(){
        LinkedList<String> linkedList=new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add(null);
        assertTrue(linkedList.remove(null));
    }

    //删除不存在的非null对象
    @Test
    void testRemoveNonNullObjectNoExits(){
        LinkedList<String> linkedList=new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        assertFalse(linkedList.remove("d"));
    }

    //删除存在的非null对象
    @Test
    void testRemoveNonNullObjectExits(){
        LinkedList<String> linkedList=new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        assertTrue(linkedList.remove("b"));
    }

}