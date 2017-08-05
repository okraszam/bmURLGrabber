//package dao;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table
//public class URLDescription implements Serializable{
//
//    @Id
//    @GeneratedValue(generator = "increment")
//    @Column
//    private int id;
//
//    @Column(length = 20)
//    private String dateOfArchivisation;
//
//    @Column(length = 50)
//    private String shortFormOfURL;
//
//    @Column(length = 2083)
//    private String complereURL;
//
//    @Column
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private URLContent urlContent;
//
//    public URLDescription() {
//    }
//
//    public URLDescription(String dateOfArchivisation, String shortFormOfURL, String complereURL) {
//        this.dateOfArchivisation = dateOfArchivisation;
//        this.shortFormOfURL = shortFormOfURL;
//        this.complereURL = complereURL;
//    }
//
//    public URLDescription(String dateOfArchivisation, String shortFormOfURL, String complereURL, URLContent urlContent) {
//        this.dateOfArchivisation = dateOfArchivisation;
//        this.shortFormOfURL = shortFormOfURL;
//        this.complereURL = complereURL;
//        this.urlContent = urlContent;
//    }
//
//}
