import java.util.UUID;
        
public class BarGenerator {
  public static String GenerateBar()
  {
      String unique = UUID.randomUUID().toString();
      String bar = unique.substring(0,13);
      return bar;
  }
}