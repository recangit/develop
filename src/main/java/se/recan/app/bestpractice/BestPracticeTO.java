package se.recan.app.bestpractice;

import org.apache.log4j.Logger;

/**
 *
 * @date 2014-jun-16
 * @author Anders Recksén (recan)
 */
public class BestPracticeTO {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private int albumsId = 0;
    private int albumsArtistId;
    private int albumsGengreId;
    private String albumsAlbum;
    private int artistsId;
    private int albumsSleeve;
    private String artistsArtist;
    private int gengresId;
    private String gengresGengre;
    private String value;

    public BestPracticeTO() {}
    
    // Populate only Gengres
    public BestPracticeTO(String value) {
        this.value = value;
    }
    
    public BestPracticeTO(String albumsAlbum, String artistsArtist, int albumsSleeve, String gengresGengre) {
        this.albumsAlbum = albumsAlbum;
        this.artistsArtist = artistsArtist;
        this.albumsSleeve = albumsSleeve;
        this.gengresGengre = gengresGengre;
    }
    
    public BestPracticeTO(int albumsArtistId, int albumsGengreId, String albumsAlbum, int albumsSleeve, int artistsId, String artistsArtist, int gengresId, String gengresGengre) {
        this.albumsArtistId = albumsArtistId;
        this.albumsGengreId = albumsGengreId;
        this.albumsAlbum = albumsAlbum;
        this.albumsSleeve = albumsSleeve;
        this.artistsId = artistsId;
        this.artistsArtist = artistsArtist;
        this.gengresId = gengresId;
        this.gengresGengre = gengresGengre;
    }

    public int getAlbumsId() {
        return albumsId;
    }

    public void setAlbumsId(int albumsId) {
        this.albumsId = albumsId;
    }

    public int getAlbumsArtistId() {
        return albumsArtistId;
    }

    public void setAlbumsArtistId(int albumsArtistId) {
        this.albumsArtistId = albumsArtistId;
    }

    public int getAlbumsGengreId() {
        return albumsGengreId;
    }

    public void setAlbumsGengreId(int albumsGengreId) {
        this.albumsGengreId = albumsGengreId;
    }

    public String getAlbumsAlbum() {
        return albumsAlbum;
    }

    public void setAlbumsAlbum(String albumsAlbum) {
        this.albumsAlbum = albumsAlbum;
    }

    public int getAlbumsSleeve() {
        return albumsSleeve;
    }

    public void setAlbumsSleeve(int albumsSleeve) {
        this.albumsSleeve = albumsSleeve;
    }

    public int getArtistsId() {
        return artistsId;
    }

    public void setArtistsId(int artistsId) {
        this.artistsId = artistsId;
    }

    public String getArtistsArtist() {
        return artistsArtist;
    }

    public void setArtistsArtist(String artistsArtist) {
        this.artistsArtist = artistsArtist;
    }

    public int getGengresId() {
        return gengresId;
    }

    public void setGengresId(int gengresId) {
        this.gengresId = gengresId;
    }

    public String getGengresGengre() {
        return gengresGengre;
    }

    public void setGengresGengre(String gengresGengre) {
        this.gengresGengre = gengresGengre;
    }
    
    public String getTmpValue() {
        return value;
    }
    
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
//        buffer.append("\nalbumsId:");
//        buffer.append(this.albumsId);
//        buffer.append("\n\talbumsArtistId:");
//        buffer.append(this.albumsArtistId);
//        buffer.append("\n\talbumsGengreId:");
//        buffer.append(this.albumsGengreId);
//        buffer.append("\n\talbumsAlbum:");
        buffer.append(albumsAlbum);
//        buffer.append("\nalbumsSleeve:");
//        buffer.append(albumsSleeve);
        
//        buffer.append("\n\tartistsId:");
//        buffer.append(this.artistsId);
//        buffer.append("\n\tartistsArtist:");
        buffer.append(" ");
        buffer.append(this.artistsArtist);
        
//        buffer.append("\n\tgengresId:");
//        buffer.append(this.gengresId);
//        buffer.append("\n\tgengresGengre:");
        buffer.append(" ");
        buffer.append(this.gengresGengre);
        
        return buffer.toString();
    }

    public boolean exists() { return albumsId != 0; }
}
