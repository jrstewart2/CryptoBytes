//package stewart.jonathan.CryptoBytes.model;
//
//import stewart.jonathan.CryptoBytes.model.Crypto;
//
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "portfolio")
//public class Portfolio {
//
//    @Id
//    private Long id;
//    private Set<Crypto> crypto = new HashSet<>();
//
//    public Portfolio() {}
//
//    public Portfolio(Long id, Set<Crypto> crypto) {
//        this.id = id;
//        this.crypto = crypto;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Override
//    public String toString() {
//        return "Portfolio{" +
//                "id=" + id +
//                ", crypto=" + crypto +
//                '}';
//    }
//}