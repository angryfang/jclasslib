/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/

package org.gjt.jclasslib.structures.attributes;

import org.gjt.jclasslib.structures.InvalidByteCodeException;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.IOException;

/**
 * Describes an <tt>LocalVariableTable</tt> attribute structure.
 *
 * @author <a href="mailto:jclasslib@ej-technologies.com">Ingo Kegel</a>, <a href="mailto:vitor.carreira@gmail.com">Vitor Carreira</a>
 *
 */
public class LocalVariableTableAttribute extends LocalVariableCommonAttribute {

    @NotNull
    @Override
    public LocalVariableCommonEntry[] getLocalVariableEntries() {
        return new LocalVariableCommonEntry[0];
    }

    @Override
    public void setLocalVariableEntries(@NotNull LocalVariableCommonEntry[] localVariableCommonEntries) {

    }

    /**
     * Name of the attribute as in the corresponding constant pool entry.
     */
    public static final String ATTRIBUTE_NAME = "LocalVariableTable";

    /**
     * Get the list of local variable associations of the parent <tt>Code</tt>
     * structure as an array of <tt>LocalVariableTableEntry</tt> structures.
     *
     * @return the array
     */
    public LocalVariableTableEntry[] getLocalVariableTable() {
        return (LocalVariableTableEntry[])getLocalVariableEntries();
    }

    /**
     * Set the list of local variable associations of the parent <tt>Code</tt>
     * structure as an array of <tt>LocalVariableTableEntry</tt> structures.
     *
     * @param localVariableTable the index
     */
    public void setLocalVariableTable(LocalVariableTableEntry[] localVariableTable) {
        this.setLocalVariableEntries(localVariableTable);
    }

    public void read(DataInput in) throws InvalidByteCodeException, IOException {

        int localVariableTableLength = in.readUnsignedShort();
        setLocalVariableEntries(new LocalVariableTableEntry[localVariableTableLength]);
        for (int i = 0; i < localVariableTableLength; i++) {
            getLocalVariableEntries()[i] = LocalVariableTableEntry.create(in, getClassFile());
        }

        if (isDebug()) debug("read ");
    }

    public int getAttributeLength() {
        return super.getAttributeLength() + getLength(getLocalVariableEntries()) * LocalVariableTableEntry.LENGTH;
    }


    protected void debug(String message) {
        super.debug(message + "LocalVariableTable attribute with " + getLength(getLocalVariableEntries()) + " entries");
    }

}
