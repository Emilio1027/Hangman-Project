package org.example;

import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class HangmanTest {


    @Test
    public void testGetSecretWord() {
        var list =  List.of("door","window","dog", "cat");
        for (int i = 0; i < 20;i++){
            String word = Hangman.getSecretWord(null,list,"1");
            assertTrue(list.contains(word));
        }

    }
    @Test
    public void testGetDictionary() {

        assertEquals(List.of("door","window","dog", "cat"),Hangman.getDictionary("src/test/java/org/example/tetsDictionary.txt"),"Dictionary not loading");
    }

    @Test
    public void testDrawHangMan() {

        String draw1 = " +-----+\n |      \n O\n";
        String draw5 = draw1 + "\\ /\n |\n/ " ;
        String draw6 = draw5 + "\\\n" ;
        assertEquals(draw1+"\n",Hangman.drawHangMan(1),"hangman not draw 1 correctly");
        assertEquals(draw5+"\n",Hangman.drawHangMan(5),"hangman not draw 5 correctly");
        assertEquals(draw6+"\n",Hangman.drawHangMan(6),"hangman not draw 6 correctly");


    }
    @Test
    public void testGetHiddenWord() {
        assertEquals("------",Hangman.getHiddenWord("Emilio",List.of()),"word not hidden correctly");
        assertEquals("E----o",Hangman.getHiddenWord("Emilio",List.of('e','r','h','o')),"word not hidden correctly");
        assertEquals("Emilio",Hangman.getHiddenWord("Emilio",List.of('M','e','l','i','O')),"word not hidden correctly");
        assertEquals("Emilio",Hangman.getHiddenWord("Emilio",List.of('m','e','l','i','o','g','t')),"word not hidden correctly");
    }

    @Test
    public void testIsWordReveal() {

        assertFalse(Hangman.isWordReveal("sleep", List.of('d','o','g')),"word revealed is given wrong results ");
        assertFalse(Hangman.isWordReveal("sleep", List.of('d','o','g','s','e')),"word revealed is given wrong results ");
        assertTrue(Hangman.isWordReveal("sleep", List.of('d','o','g','s','e','l','p')),"word revealed is given wrong results ");
    }
}