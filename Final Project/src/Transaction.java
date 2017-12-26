import javafx.beans.property.SimpleStringProperty;

public class Transaction {
  private final SimpleStringProperty id;
  private final SimpleStringProperty date;
  private final SimpleStringProperty total;
  
  
  public Transaction(
      String id,
      String date,
      String total
      ) {
    this.id = new SimpleStringProperty( id );
    this.date = new SimpleStringProperty( date );
    this.total = new SimpleStringProperty( total );
  }
  
  
  public String getId() {
    return id.get();
  }
  
  public void setId(String id) {
    this.id.set( id );
  }
  
  
  
  public String getDate() {
    return date.get();
  }
  
  public void setDate(String date) {
    this.date.set( date );
  }
  
  
  
  public String getTotal() {
    return total.get();
  }
  public void setTotal(String total) {
    this.total.set( total );
  }
}