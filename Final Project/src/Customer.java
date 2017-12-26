import javafx.beans.property.SimpleStringProperty;
import javax.swing.text.MaskFormatter;
import java.lang.Object;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
  private final SimpleStringProperty id;
  private final SimpleStringProperty firstName;
  private final SimpleStringProperty lastName;
  private final SimpleStringProperty phone;
  private final SimpleStringProperty email;
  
   
  public Customer(
      String id,
      String firstName,
      String lastName,
      String phone,
      String email
      ) {
    this.id = new SimpleStringProperty( id );
    this.firstName = new SimpleStringProperty( firstName );
    this.lastName = new SimpleStringProperty( lastName );
    this.phone = new SimpleStringProperty( phone );
    this.email = new SimpleStringProperty( email );
  }
  
  
  public String getFirstName() {
    return firstName.get();
  }
  public void setFirstName(String firstName) {
    this.firstName.set( firstName );
  }
  
  
  public String getLastName() {
    return lastName.get();
  }
  public void setLastName(String lastName) {
    this.lastName.set( lastName );
  }
  
  
  public String getPhone() {
    return phone.get();
  }
  
  public void setPhone(String phone) {
                  //String phoneMask= "###-###-####";
   this.phone.set( phone );
                  //MaskFormatter maskFormatter = new MaskFormatter(phoneMask);
                  //maskFormatter.setValueContainsLiteralCharacters(false);
                  //maskFormatter.valueToString( phoneNumber ); 
  }
  
  public String getId() {
      return id.get();
  }
  public void setId(String id) {
      this.id.set( id );
  }
  
  
  public String getEmail() {
      return email.get();
  }
  
  public void setEmail(String email) {
     //email.matcher("^.+@.+\\..+$");
       this.email.set( email );
  }
  
//public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    //Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

//public static boolean validate(String email) {
      //  Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
       // return matcher.find();
//}
  
  
}