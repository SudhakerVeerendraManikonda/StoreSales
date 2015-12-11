package assign4;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class StoreSales
{
    private Map<String, Double> productAndPrices =
    new HashMap<String, Double>();
    
    private Map<String, Double> taxAreaAndRates =
    new HashMap<String, Double>();
    
    private Map<String, Double> productTaxAreaAndReduction =
    new HashMap<String, Double>();
    
    public StoreSales(Map<String, Double> _productAndPrices,
                      Map<String, Double> _taxAreaAndRates,
                      Map<String, Double> _productTaxAreaAndReduction)
    {
        productAndPrices = _productAndPrices;
        taxAreaAndRates = _taxAreaAndRates;
        productTaxAreaAndReduction = _productTaxAreaAndReduction;
    }
    
    public double getTotalPrice(List<String> products, String taxArea)
    {
        double totalPrice = 0.0;
        for ( int index = 0; index < products.size(); index++ )
            totalPrice = totalPrice + getPrice(products.get(index), taxArea);
        return totalPrice;
    }

    private double getPrice(String product, String taxArea)
    {
        if(productAndPrices.containsKey(product) && 
			  taxAreaAndRates.containsKey(taxArea))
        {
            double salesPrice = productAndPrices.get(product);
            double taxRate = taxAreaAndRates.get(taxArea);
            double taxReduction = getTaxReduction(taxArea, product);

            return salesPrice + salesPrice * (taxRate - taxReduction);
        }
        return 0.0;
    }

    private double getTaxReduction(String taxArea, String product)
    {
        String productTaxReduction = product + taxArea ;
        return productTaxAreaAndReduction.containsKey(productTaxReduction) ?
                    productTaxAreaAndReduction.get(productTaxReduction) : 0;
    }
}
