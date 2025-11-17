/*
 *  JFLAP - Formal Languages and Automata Package
 *
 *  Susan H. Rodger
 *  Computer Science Department
 *  Duke University
 *  August 27, 2009
 *
 *  Copyright (c) 2002-2009
 *  All rights reserved.
 *
 *  JFLAP is open source software. Please see the LICENSE for terms.
 *
 */

package automata.pda;

import java.io.Serializable;

/**
 * A character stack/queue. Allows pushing (enqueue at tail) and popping
 * (dequeue from front) of individual <CODE>char</CODE>s.
 *
 * @author Thomas Finley (modificado)
 */
public class CharacterStack implements Serializable {
    /**
     * Instantiates an empty character stack.
     */
    public CharacterStack() {

    }

    /**
     * Instantiates a character stack that is a copy of a given character stack.
     *
     * @param stack
     *            the character stack to copy
     */
    public CharacterStack(CharacterStack stack) {
        this.buffer = new StringBuffer(stack.buffer.toString());
    }

    /**
     * Pushes a character (adds to the tail/end).
     *
     * @param character
     *            the character to push onto the stack/queue


    public void push(char character) { //PDA original para pushear un caracter
        buffer.insert(0, character);
        cachedHash = 0xdeadbeef;
    }
    */

    public void push(char character) {
        // Cambiado: añadir al final para que los nuevos símbolos vayan después de los existentes.
        buffer.append(character);
        cachedHash = 0xdeadbeef;
    }

    /**
     * Pushes a string onto the structure. The string is appended in the given
     * order (first character of the string will be earlier than subsequent ones).
     *
     * @param string
     *            the strings characters which we push onto the stack/queue


    public void push(String string) { //PDA original para pushear cadena
        buffer.insert(0, string);
        cachedHash = 0xdeadbeef;
    }
    */
    public void push(String string) {
        // Cambiado: añadir la cadena completa al final
        buffer.append(string);
        cachedHash = 0xdeadbeef;
    }

    /**
     * Clears the stack.
     */
    public void clear() {
        buffer = new StringBuffer();
        cachedHash = 0xdeadbeef;
    }

    /**
     * Returns the number of characters on this stack.
     *
     * @return the number of characters on this stack
     */
    public int height() {
        return buffer.length();
    }

    /**
     * Pops a character from this stack. This will remove that character from
     * the stack (front / index 0).
     *
     * @return the front character in the stack, or 0 if there is no character in
     *         the stack
     */
    public char pop() {
        char[] toReturn = new char[1];
        buffer.getChars(0, 1, toReturn, 0);
        buffer.deleteCharAt(0);
        return toReturn[0];
    }

    /**
     * Pops a number of characters off the stack (from the front), and returns the result as a
     * string. The first character in the string is the first character popped
     * off the stack.
     *
     * @param number
     *            the number of elements to pop off the stack
     * @return a string of length <CODE>number</CODE> or <CODE>null</CODE>
     *         if there are not <CODE>number</CODE> characters left on the
     *         stack
     */
    public String pop(int number) {
        if (buffer.length() < number)
            return null;
        char[] c = new char[number];
        buffer.getChars(0, number, c, 0);
        buffer.delete(0, number);
        return new String(c);
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of this object
     */
    public String toString() {
        return buffer.toString();
    }

    /**
     * Predictably, two character stacks are equal if they have the same
     * characters in the stack in the same order, etc.
     *
     * @param stack
     *            the stack to check against for equality
     * @return <CODE>true</CODE> if the stacks are equal, <CODE>false</CODE>
     *         otherwise
     */
    public boolean equals(Object stack) {
        try {
            return ((CharacterStack) stack).buffer.toString().equals(
                    buffer.toString());
        } catch (ClassCastException e) {
            return false;
        }
    }

    /**
     * Returns a hash value for this character stack.
     *
     * @return a hash value for this character stack
     */
    public int hashCode() {
        if (cachedHash != 0xdeadbeef)
            return cachedHash;
        return cachedHash = buffer.toString().hashCode();
    }

    /** The string buffer. Front is index 0; tail is at the end. */
    private StringBuffer buffer = new StringBuffer();

    /** The cached hash value. */
    private int cachedHash = 0xdeadbeef;
}
