//package dao;
//
//import javax.persistence.*;
//import java.io.File;
//
//@Entity
//@Table
//public class URLContent {
//
//    @Id
//    @GeneratedValue(generator = "increment")
//    @Column
//    private int id;
//
//    @Column
//    @OneToOne(fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    private int id_urlDescription;
//
//    @Column
//    private File urlContent;
//
//    public URLContent() {
//    }
//
//    public URLContent(File urlContent) {
//        this.urlContent = urlContent;
//    }
//
//}
