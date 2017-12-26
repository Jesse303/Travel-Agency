import javafx.beans.property.SimpleStringProperty;

public class Products {
  private final SimpleStringProperty prodID;
  private final SimpleStringProperty packageDeal;
  private final SimpleStringProperty price;
  
  
  public Products(
      String prodID,
      String packageDeal,
      String price
      ) {
    this.prodID = new SimpleStringProperty( prodID );
    this.packageDeal = new SimpleStringProperty( packageDeal );
    this.price = new SimpleStringProperty( price );
  }
  
  
  public String getProdID() {
    return prodID.get();
  }
  
  public void setProdID(String prodID) {
    this.prodID.set( prodID );
  }
  
  public String getPackageDeal() {
    return packageDeal.get();
  }
  
  public void setPackageDeal(String packageDeal) {
    this.packageDeal.set( packageDeal );
  }
  
  public String getPrice() {
    return price.get();
  }
  public void setPrice(String price) {
    this.price.set( price );
  }
}