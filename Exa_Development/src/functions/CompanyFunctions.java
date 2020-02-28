package functions;

import beans.Company;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class CompanyFunctions {
    private List<Company> companies;
    
    public void loadData() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "company_data.csv");
        
        companies = Files.lines(path)
                  .skip(1)
                  .map(Company::new)
                  .collect(Collectors.toList());
        
//        companies.forEach(System.out::println);
//        companies.forEach(c -> System.out.println(c));
    }
    
    public void performStreamingTests() {
        //sortieren
        List<Company> sorted = companies.stream()
                  .sorted(Comparator.comparing(Company::getCountry)
                                    .thenComparing(Company::getFounded))
                  .collect(Collectors.toList());
        
        //filtern
        List<Company> filtered = companies.stream()
                  .filter( c -> c.getFounded().isBefore(LocalDate.of(2000, 1, 1)))
                  
                  .sorted(Comparator.comparing(Company::getCountry)
                                    .thenComparing(Company::getFounded))
                  .collect(Collectors.toList());
        
//        filtered.forEach(System.out::println);
        
        //Sum of all turnover of all companies
        double sum = companies.stream()
                  .mapToDouble(Company::getTurnover)
                  .sum();
        
//        System.out.println(sum);

        //company namen
        String[] countries = companies.stream()
                  .map(Company::getCountry)
                  .sorted()
                  .distinct()
                  .toArray(String[]::new);
        
        Arrays.stream(countries).forEach(System.out::println);
    }
    
    public static void main(String[] args) {
        CompanyFunctions cf = new CompanyFunctions();
        try {
            cf.loadData();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        cf.performStreamingTests();
        
    }
    
}
