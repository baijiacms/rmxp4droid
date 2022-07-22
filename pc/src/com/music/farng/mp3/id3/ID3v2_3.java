package com.music.farng.mp3.id3;

import com.music.farng.mp3.AbstractMP3Tag;
import com.music.farng.mp3.InvalidTagException;
import com.music.farng.mp3.MP3File;
import com.music.farng.mp3.TagConstant;
import com.music.farng.mp3.TagException;
import com.music.farng.mp3.TagNotFoundException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

/**
 * <p class=t> The two biggest design goals were to be able to implement ID3v2 without disturbing old software too much
 * and that ID3v2 should be as flexible and expandable as possible. </p>
 * <p/>
 * <p class=t> The first criterion is met by the simple fact that the <a href="#MPEG">MPEG</a> decoding software uses a
 * syncsignal, embedded in the audiostream, to 'lock on to' the audio. Since the ID3v2 tag doesn't contain a valid
 * syncsignal, no software will attempt to play the tag. If, for any reason, coincidence make a syncsignal appear within
 * the tag it will be taken care of by the 'unsynchronisation scheme' described in <a href="#sec5">section 5</a>. </p>
 * <p/>
 * <p class=t> The second criterion has made a more noticeable impact on the design of the ID3v2 tag. It is constructed
 * as a container for several information blocks, called frames, whose format need not be known to the software that
 * encounters them. At the start of every frame there is an identifier that explains the frames' format and content, and
 * a size descriptor that allows software to skip unknown frames. </p>
 * <p/>
 * <p class=t> If a total revision of the ID3v2 tag should be needed, there is a version number and a size descriptor in
 * the ID3v2 header. </p>
 * <p/>
 * <p class=t> The ID3 tag described in this document is mainly targeted at files encoded with <a
 * href="#MPEG">MPEG</a>-1/2 layer I, <a href="#MPEG">MPEG</a>-1/2 layer II, <a href="#MPEG">MPEG</a>-1/2 layer III and
 * MPEG-2.5, but may work with other types of encoded audio. </p>
 * <p/>
 * <p class=t> The bitorder in ID3v2 is most significant bit first (MSB). The byteorder in multibyte numbers is most
 * significant byte first (e.g. $12345678 would be encoded $12 34 56 78). </p>
 * <p/>
 * <p class=t> It is permitted to include padding after all the final frame (at the end of the ID3 tag), making the size
 * of all the frames together smaller than the size given in the head of the tag. A possible purpose of this padding is
 * to allow for adding a few additional frames or enlarge existing frames within the tag without having to rewrite the
 * entire file. The value of the padding bytes must be $00. </p> <p class=t> The ID3v2 tag header, which should be the
 * first information in the file, is 10 bytes as follows: </p>
 * <p/>
 * <p><center> <table border=0> <tr><td nowrap>ID3v2/file identifier</td><td rowspan=4>&nbsp;</td><td
 * width="100%">"ID3"</td></tr> <tr><td>ID3v2 version</td><td>$03 00</td></tr> <tr><td>ID3v2
 * flags</td><td>%abc00000</td></tr> <tr><td>ID3v2 size</td><td>4 * %0xxxxxxx</td></tr> </table> </center>
 * <p/>
 * <p class=t> The first three bytes of the tag are always "ID3" to indicate that this is an ID3v2 tag, directly
 * followed by the two version bytes. The first byte of ID3v2 version is it's major version, while the second byte is
 * its revision number. In this case this is ID3v2.3.0. All revisions are backwards compatible while major versions are
 * not. If software with ID3v2.2.0 and below support should encounter version three or higher it should simply ignore
 * the whole tag. Version and revision will never be $FF. </p>
 * <p/>
 * <p class=t> The version is followed by one the ID3v2 flags field, of which currently only three flags are used. </p>
 * <p/>
 * <p class=t> a - Unsynchronisation </p>
 * <p/>
 * <p class=ind> Bit 7 in the 'ID3v2 flags' indicates whether or not unsynchronisation is used (see <a
 * href="#sec5">section 5</a> for details); a set bit indicates usage.</p>
 * <p/>
 * <p class=t> b - Extended header </p>
 * <p/>
 * <p class=ind> The second bit (bit 6) indicates whether or not the header is followed by an extended header. The
 * extended header is described in <a href="#sec3.2">section 3.2</a>. </p>
 * <p/>
 * <p class=t> c - Experimental indicator </p>
 * <p/>
 * <p class=ind> The third bit (bit 5) should be used as an 'experimental indicator'. This flag should always be set
 * when the tag is in an experimental stage. </p>
 * <p/>
 * <p class=t> All the other flags should be cleared. If one of these undefined flags are set that might mean that the
 * tag is not readable for a parser that does not know the flags function. </p>
 * <p/>
 * <p class=t> The ID3v2 tag size is encoded with four bytes where the most significant bit (bit 7) is set to zero in
 * every byte, making a total of 28 bits. The zeroed bits are ignored, so a 257 bytes long tag is represented as $00 00
 * 02 01. </p>
 * <p/>
 * <p class=t> The ID3v2 tag size is the size of the complete tag after unsychronisation, including padding, excluding
 * the header but not excluding the extended header (total tag size - 10). Only 28 bits (representing up to 256MB) are
 * used in the size description to avoid the introducuction of 'false syncsignals'. </p>
 * <p/>
 * <p class=t> An ID3v2 tag can be detected with the following pattern:<br> $49 44 33 yy yy xx zz zz zz zz<br> Where yy
 * is less than $FF, xx is the 'flags' byte and zz is less than $80. </p>
 *
 * @author Eric Farng
 * @version $Revision: 1.5 $
 */
public class ID3v2_3 extends ID3v2_2 {

    protected boolean crcDataFlag = false;
    protected boolean experimental = false;
    protected boolean extended = false;
    protected int crcData = 0;
    protected int paddingSize = 0;

    /**
     * Creates a new ID3v2_3 object.
     */
    public ID3v2_3() {
        setMajorVersion((byte) 2);
        setRevision((byte) 3);
    }

    /**
     * Creates a new ID3v2_3 object.
     */
    public ID3v2_3(final ID3v2_3 copyObject) {
        super(copyObject);
        this.crcDataFlag = copyObject.crcDataFlag;
        this.experimental = copyObject.experimental;
        this.extended = copyObject.extended;
        this.crcData = copyObject.crcData;
        this.paddingSize = copyObject.paddingSize;
    }

    /**
     * Creates a new ID3v2_3 object.
     */
    public ID3v2_3(final AbstractMP3Tag mp3tag) {
        if (mp3tag != null) {
            final ID3v2_4 convertedTag;
            if ((mp3tag instanceof ID3v2_4 == false) && (mp3tag instanceof ID3v2_3 == true)) {
                throw new UnsupportedOperationException("Copy Constructor not called. Please type cast the argument");
            }
            if (mp3tag instanceof ID3v2_4) {
                convertedTag = (ID3v2_4) mp3tag;
            } else {
                convertedTag = new ID3v2_4(mp3tag);
            }
            this.extended = convertedTag.extended;
            this.experimental = convertedTag.experimental;
            this.crcDataFlag = convertedTag.crcDataFlag;
            this.crcData = convertedTag.crcData;
            this.paddingSize = convertedTag.paddingSize;
            this.compression = convertedTag.compression;
            this.unsynchronization = convertedTag.unsynchronization;
            final AbstractID3v2 id3tag = convertedTag;
            final Iterator iterator = id3tag.getFrameIterator();
            AbstractID3v2Frame frame;
            ID3v2_3Frame newFrame;
            while (iterator.hasNext()) {
                frame = (AbstractID3v2Frame) iterator.next();
                newFrame = new ID3v2_3Frame(frame);
                this.setFrame(newFrame);
            }
        }
    }

    /**
     * Creates a new ID3v2_3 object.
     */
    public ID3v2_3(final RandomAccessFile file) throws TagException, IOException {
        this.read(file);
    }

    public String getIdentifier() {
        return "ID3v2.30";
    }

    public int getSize() {
        int size = 3 + 2 + 1 + 4;
        if (this.extended) {
            if (this.crcDataFlag) {
                size += (4 + 2 + 4 + 4);
            } else {
                size += (4 + 2 + 4);
            }
        }
        final Iterator iterator = this.getFrameIterator();
        AbstractID3v2Frame frame;
        while (iterator.hasNext()) {
            frame = (AbstractID3v2Frame) iterator.next();
            size += frame.getSize();
        }
        return size;
    }

    public void append(final AbstractMP3Tag tag) {
        if (tag instanceof ID3v2_3) {
            this.experimental = ((ID3v2_3) tag).experimental;
            this.extended = ((ID3v2_3) tag).extended;
            this.crcDataFlag = ((ID3v2_3) tag).crcDataFlag;
            this.paddingSize = ((ID3v2_3) tag).paddingSize;
            this.crcData = ((ID3v2_3) tag).crcData;
        }
        super.append(tag);
    }

    public boolean equals(final Object obj) {
        if ((obj instanceof ID3v2_3) == false) {
            return false;
        }
        final ID3v2_3 id3v2_3 = (ID3v2_3) obj;
        if (this.crcData != id3v2_3.crcData) {
            return false;
        }
        if (this.crcDataFlag != id3v2_3.crcDataFlag) {
            return false;
        }
        if (this.experimental != id3v2_3.experimental) {
            return false;
        }
        if (this.extended != id3v2_3.extended) {
            return false;
        }
        if (this.paddingSize != id3v2_3.paddingSize) {
            return false;
        }
        return super.equals(obj);
    }

    public void overwrite(final AbstractMP3Tag tag) {
        if (tag instanceof ID3v2_3) {
            this.experimental = ((ID3v2_3) tag).experimental;
            this.extended = ((ID3v2_3) tag).extended;
            this.crcDataFlag = ((ID3v2_3) tag).crcDataFlag;
            this.paddingSize = ((ID3v2_3) tag).paddingSize;
            this.crcData = ((ID3v2_3) tag).crcData;
        }
        super.overwrite(tag);
    }

    public void read(final RandomAccessFile file) throws TagException, IOException {
        final int size;
        final byte[] buffer = new byte[4];
        if (seek(file) == false) {
            throw new TagNotFoundException(getIdentifier() + " tag not found");
        }

        // read the major and minor @version number & flags byte
        file.read(buffer, 0, 3);
        if ((buffer[0] != 3) || (buffer[1] != 0)) {
            throw new TagNotFoundException(getIdentifier() + " tag not found");
        }
        setMajorVersion(buffer[0]);
        setRevision(buffer[1]);
        this.unsynchronization = (buffer[2] & TagConstant.MASK_V23_UNSYNCHRONIZATION) != 0;
        this.extended = (buffer[2] & TagConstant.MASK_V23_EXTENDED_HEADER) != 0;
        this.experimental = (buffer[2] & TagConstant.MASK_V23_EXPERIMENTAL) != 0;

        // read the size
        file.read(buffer, 0, 4);
        size = byteArrayToSize(buffer);
        final long filePointer = file.getFilePointer();
        if (this.extended) {
            // int is 4 bytes.
            final int extendedHeaderSize = file.readInt();

            // the extended header is only 6 or 10 bytes.
            if (extendedHeaderSize != 6 && extendedHeaderSize != 10) {
                throw new InvalidTagException("Invalid Extended Header Size.");
            }
            file.read(buffer, 0, 2);
            this.crcDataFlag = (buffer[0] & TagConstant.MASK_V23_CRC_DATA_PRESENT) != 0;

            // if it's 10 bytes, the CRC flag must be set
            // and if it's 6 bytes, it must not be set
            if (((extendedHeaderSize == 10) && (this.crcDataFlag == false)) ||
                ((extendedHeaderSize == 6) && (this.crcDataFlag == true))) {
                throw new InvalidTagException("CRC Data flag not set correctly.");
            }
            this.paddingSize = file.readInt();
            if ((extendedHeaderSize == 10) && this.crcDataFlag) {
                this.crcData = file.readInt();
            }
        }
        ID3v2_3Frame next;
        this.clearFrameMap();

        // read all the frames.
        this.setFileReadBytes(size);
        AbstractID3v2.resetPaddingCounter();
        while ((file.getFilePointer() - filePointer) <= size) {
            try {
                next = new ID3v2_3Frame(file);
                final String id = next.getIdentifier();
                if (this.hasFrame(id)) {
                    this.appendDuplicateFrameId(id + "; ");
                    this.incrementDuplicateBytes(this.getFrame(id).getSize());
                }
                this.setFrame(next);
            } catch (InvalidTagException ex) {
                if (ex.getMessage().equals("Found empty frame")) {
                    this.incrementEmptyFrameBytes(10);
                } else {
                    this.incrementInvalidFrameBytes();
                }
            }
        }
        this.setPaddingSize(getPaddingCounter());
    }

    public boolean seek(final RandomAccessFile file) throws IOException {
        final byte[] buffer = new byte[3];
        file.seek(0);

        // read the tag if it exists
        file.read(buffer, 0, 3);
        final String tag = new String(buffer, 0, 3);
        if (tag.equals("ID3") == false) {
            return false;
        }

        // read the major and minor @version number
        file.read(buffer, 0, 2);

        // read back the @version bytes so we can read and save them later
        file.seek(file.getFilePointer() - 2);
        return ((buffer[0] == 3) && (buffer[1] == 0));
    }

    public String toString() {
        final Iterator iterator = this.getFrameIterator();
        AbstractID3v2Frame frame;
        String str = getIdentifier() + " " + this.getSize() + "\n";
        str += ("compression        = " + this.compression + "\n");
        str += ("unsynchronization  = " + this.unsynchronization + "\n");
        str += ("crcData            = " + this.crcData + "\n");
        str += ("crcDataFlag        = " + this.crcDataFlag + "\n");
        str += ("experimental       = " + this.experimental + "\n");
        str += ("extended           = " + this.extended + "\n");
        str += ("paddingSize        = " + this.paddingSize + "\n");
        while (iterator.hasNext()) {
            frame = (ID3v2_3Frame) iterator.next();
            str += (frame.toString() + "\n");
        }
        return str + "\n";
    }

    public void write(final AbstractMP3Tag tag) {
        if (tag instanceof ID3v2_3) {
            this.experimental = ((ID3v2_3) tag).experimental;
            this.extended = ((ID3v2_3) tag).extended;
            this.crcDataFlag = ((ID3v2_3) tag).crcDataFlag;
            this.paddingSize = ((ID3v2_3) tag).paddingSize;
            this.crcData = ((ID3v2_3) tag).crcData;
        }
        super.write(tag);
    }

    public void write(final RandomAccessFile file) throws IOException {
        final String str;
        final Iterator iterator;
        final byte[] buffer = new byte[6];
        final MP3File mp3 = new MP3File();
        mp3.seekMP3Frame(file);
        final long mp3start = file.getFilePointer();
        file.seek(0);
        ID3v2_3Frame frame;
        str = "ID3";
        for (int i = 0; i < str.length(); i++) {
            buffer[i] = (byte) str.charAt(i);
        }
        buffer[3] = 3;
        buffer[4] = 0;
        if (this.unsynchronization) {
            buffer[5] |= TagConstant.MASK_V23_UNSYNCHRONIZATION;
        }
        if (this.extended) {
            buffer[5] |= TagConstant.MASK_V23_EXTENDED_HEADER;
        }
        if (this.experimental) {
            buffer[5] |= TagConstant.MASK_V23_EXPERIMENTAL;
        }
        file.write(buffer);

        // write size
        file.write(sizeToByteArray((int) mp3start - 10));
        if (this.extended) {
            if (this.crcDataFlag) {
                file.writeInt(10);
                buffer[0] = 0;
                buffer[0] |= TagConstant.MASK_V23_CRC_DATA_PRESENT;
                file.write(buffer, 0, 2);
                file.writeInt(this.paddingSize);
                file.writeInt(this.crcData);
            } else {
                file.writeInt(6);
                file.write(buffer, 0, 2);
                file.writeInt(this.paddingSize);
            }
        }

        // write all frames
        iterator = this.getFrameIterator();
        while (iterator.hasNext()) {
            frame = (ID3v2_3Frame) iterator.next();
            frame.write(file);
        }
    }

    public String getSongTitle() {
        String text = "";
        AbstractID3v2Frame frame = getFrame("TIT2");
        if (frame != null) {
            FrameBodyTIT2 body = (FrameBodyTIT2) frame.getBody();
            text = body.getText();
        }
        return text.trim();
    }

    public String getLeadArtist() {
        String text = "";
        AbstractID3v2Frame frame = getFrame("TPE1");
        if (frame != null) {
            FrameBodyTPE1 body = (FrameBodyTPE1) frame.getBody();
            text = body.getText();
        }
        return text.trim();
    }

    public String getAlbumTitle() {
        String text = "";
        AbstractID3v2Frame frame = getFrame("TALB");
        if (frame != null) {
            FrameBodyTALB body = (FrameBodyTALB) frame.getBody();
            text = body.getText();
        }
        return text.trim();
    }

    public String getYearReleased() {
        String text = "";
        AbstractID3v2Frame frame = getFrame("TYER");
        if (frame != null) {
            FrameBodyTYER body = (FrameBodyTYER) frame.getBody();
            text = body.getText();
        }
        return text.trim();
    }

    public String getSongComment() {
        String text = "";
        AbstractID3v2Frame frame = getFrame("COMM" + ((char) 0) + "eng" + ((char) 0) + "");
        if (frame != null) {
            FrameBodyCOMM body = (FrameBodyCOMM) frame.getBody();
            text = body.getText();
        }
        return text.trim();
    }

    public String getSongGenre() {
        String text = "";
        AbstractID3v2Frame frame = getFrame("TCON");
        if (frame != null) {
            FrameBodyTCON body = (FrameBodyTCON) frame.getBody();
            text = body.getText();
        }
        return text.trim();
    }

    public String getTrackNumberOnAlbum() {
        String text = "";
        AbstractID3v2Frame frame = getFrame("TRCK");
        if (frame != null) {
            FrameBodyTRCK body = (FrameBodyTRCK) frame.getBody();
            text = body.getText();
        }
        return text.trim();
    }

    public String getSongLyric() {
        String text = "";
        AbstractID3v2Frame frame = getFrame("SYLT");
        if (frame != null) {
            FrameBodySYLT body = (FrameBodySYLT) frame.getBody();
            text = body.getLyric();
        }
        if (text == "") {
            frame = getFrame("USLT" + ((char) 0) + "eng" + ((char) 0) + "");
            if (frame != null) {
                FrameBodyUSLT body = (FrameBodyUSLT) frame.getBody();
                text = body.getLyric();
            }
        }
        return text.trim();
    }

    public String getAuthorComposer() {
        String text = "";
        AbstractID3v2Frame frame = getFrame("TCOM");
        if (frame != null) {
            FrameBodyTCOM body = (FrameBodyTCOM) frame.getBody();
            text = body.getText();
        }
        return text.trim();
    }

    public void setSongTitle(String songTitle) {
        AbstractID3v2Frame field = getFrame("TIT2");
        if (field == null) {
            field = new ID3v2_3Frame(new FrameBodyTIT2((byte) 0, songTitle.trim()));
            setFrame(field);
        } else {
            ((FrameBodyTIT2) field.getBody()).setText(songTitle.trim());
        }
    }

    public void setLeadArtist(String leadArtist) {
        AbstractID3v2Frame field = getFrame("TPE1");
        if (field == null) {
            field = new ID3v2_3Frame(new FrameBodyTPE1((byte) 0, leadArtist.trim()));
            setFrame(field);
        } else {
            ((FrameBodyTPE1) field.getBody()).setText(leadArtist.trim());
        }
    }

    public void setAlbumTitle(String albumTitle) {
        AbstractID3v2Frame field = getFrame("TALB");
        if (field == null) {
            field = new ID3v2_3Frame(new FrameBodyTALB((byte) 0, albumTitle.trim()));
            setFrame(field);
        } else {
            ((FrameBodyTALB) field.getBody()).setText(albumTitle.trim());
        }
    }

    public void setYearReleased(String yearReleased) {
        AbstractID3v2Frame field = getFrame("TYER");
        if (field == null) {
            field = new ID3v2_3Frame(new FrameBodyTYER((byte) 0, yearReleased.trim()));
            setFrame(field);
        } else {
            ((FrameBodyTYER) field.getBody()).setText(yearReleased.trim());
        }
    }

    public void setSongComment(String songComment) {
        AbstractID3v2Frame field = getFrame("COMM");
        if (field == null) {
            field = new ID3v2_3Frame(new FrameBodyCOMM((byte) 0, "ENG", "", songComment.trim()));
            setFrame(field);
        } else {
            ((FrameBodyCOMM) field.getBody()).setText(songComment.trim());
        }
    }

    public void setSongGenre(String songGenre) {
        AbstractID3v2Frame field = getFrame("TCON");
        if (field == null) {
            field = new ID3v2_3Frame(new FrameBodyTCON((byte) 0, songGenre.trim()));
            setFrame(field);
        } else {
            ((FrameBodyTCON) field.getBody()).setText(songGenre.trim());
        }
    }

    public void setTrackNumberOnAlbum(String trackNumberOnAlbum) {
        AbstractID3v2Frame field = getFrame("TRCK");
        if (field == null) {
            field = new ID3v2_3Frame(new FrameBodyTRCK((byte) 0, trackNumberOnAlbum.trim()));
            setFrame(field);
        } else {
            ((FrameBodyTRCK) field.getBody()).setText(trackNumberOnAlbum.trim());
        }
    }

    public void setSongLyric(String songLyrics) {
        AbstractID3v2Frame field = getFrame("SYLT");
        if (field == null) {
            field = new ID3v2_3Frame(new FrameBodyUSLT((byte) 0, "ENG", "", songLyrics.trim()));
            setFrame(field);
        } else {
            ((FrameBodyUSLT) field.getBody()).setLyric(songLyrics.trim());
        }
    }

    public void setAuthorComposer(String authorComposer) {
        AbstractID3v2Frame field = getFrame("TCOM");
        if (field == null) {
            field = new ID3v2_3Frame(new FrameBodyTCOM((byte) 0, authorComposer.trim()));
            setFrame(field);
        } else {
            ((FrameBodyTCOM) field.getBody()).setText(authorComposer.trim());
        }
    }
}