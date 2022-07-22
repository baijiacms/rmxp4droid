package com.music.farng.mp3.object;

/**
 * ID3v2 and Lyrics3v2 tags have individual fields <code>AbstractMP3Fragment</code>s Then each fragment is broken down
 * in to individual <code>AbstractMP3Object</code>s
 *
 * @author Eric Farng
 * @version $Revision: 1.5 $
 */
public class ObjectByteArraySizeTerminated extends AbstractMP3Object {

    /**
     * Creates a new ObjectByteArraySizeTerminated object.
     */
    public ObjectByteArraySizeTerminated(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Creates a new ObjectByteArraySizeTerminated object.
     */
    public ObjectByteArraySizeTerminated(final ObjectByteArraySizeTerminated object) {
        super(object);
    }

    public int getSize() {
        int len = 0;
        if (this.value != null) {
            len = ((byte[]) this.value).length;
        }
        return len;
    }

    public boolean equals(final Object obj) {
        if (obj instanceof ObjectByteArraySizeTerminated == false) {
            return false;
        }
        return super.equals(obj);
    }

    public void readByteArray(final byte[] arr, final int offset) {
        if (arr == null) {
            throw new NullPointerException("Byte array is null");
        }
        if ((offset < 0) || (offset >= arr.length)) {
            throw new IndexOutOfBoundsException("Offset to byte array is out of bounds: offset = " +
                                                offset +
                                                ", array.length = " +
                                                arr
                                                        .length);
        }
        final int len = arr.length - offset;
        this.value = new byte[len];
        System.arraycopy(arr, offset, this.value, 0, len);
    }

    public String toString() {
        return getSize() + " bytes";
    }

    public byte[] writeByteArray() {
        return (byte[]) this.value;
    }
}