package ve.com.PROJECT.msd.model.attachment;

import ve.com.PROJECT.msd.enums.AttachmentType;



public class AudioAttachment extends Attachment {

    private String audioFilename;

    public AudioAttachment() { /* Parameter-less constructor for audio attachment creation */ }
    public AudioAttachment(String audioFilename) {
        this.audioFilename = audioFilename;
    }
    public AudioAttachment(int id, int reminderId, String audioFilename) {
        super(id, reminderId);
        this.audioFilename = audioFilename;
    }

    @Override
    public AttachmentType getType() {
        return AttachmentType.AUDIO;
    }

    public String getAudioFilename() {
        return audioFilename;
    }
    public void setAudioFilename(String audioFilename) {
        this.audioFilename = audioFilename;
    }
}
