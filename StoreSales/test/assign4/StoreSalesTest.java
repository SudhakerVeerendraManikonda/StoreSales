package assign4;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import java.util.Arrays;
import java.util.List;

public class StoreSalesTest extends TestCase
{
    Map<String, Double> productAndPrices =
            new HashMap<String, Double>();

    Map<String, Double> taxAreaAndRates =
            new HashMap<String, Double>();

    Map<String, Double> productTaxAreaAndReduction =
            new HashMap<String, Double>();

    public void testCanary()
    {
        assert(true);
    }

    public void setUp()
    {
        productAndPrices.put("TShirt", 100.00);
        productAndPrices.put("Souvenir", 50.00);
        productAndPrices.put("KeyChain", 25.00);
        taxAreaAndRates.put("TXArea", 0.0825);
        taxAreaAndRates.put("CAArea", 0.0750);
        productTaxAreaAndReduction.put("SouvenirCAArea", 0.01);
        productTaxAreaAndReduction.put("KeyChainCAArea", 0.02);
    }

    public void testGetTotalPriceForOneProductWithNoReduction()
    {
        List<String> products = Arrays.asList("TShirt");
        String taxArea = "TXArea";

        StoreSales _sales = new StoreSales(productAndPrices, taxAreaAndRates, productTaxAreaAndReduction);
        assertEquals(108.25, _sales.getTotalPrice(products, taxArea));
    }

    public void testGetTotalPriceForTwoProductsWithReductionOnOneProduct()
    {
        List<String> products = Arrays.asList("TShirt", "Souvenir");
        String taxArea = "CAArea";

        StoreSales _sales = new StoreSales(productAndPrices, taxAreaAndRates, productTaxAreaAndReduction);
        assertEquals(160.75, _sales.getTotalPrice(products, taxArea));
    }

    public void testGetTotalPriceListOfProductsThatAreBothSoldAndNotSoldInStore()
    {
        List<String> products = Arrays.asList("Watch", "Souvenir");
        String taxArea = "CAArea";

        StoreSales _sales = new StoreSales(productAndPrices, taxAreaAndRates, productTaxAreaAndReduction);
        assertEquals(53.25, _sales.getTotalPrice(products, taxArea));
    }

    public void testTotalPriceForTwoProductsWithReductionOnBothProductsInOneArea()
    {
        List<String> products = Arrays.asList("KeyChain", "Souvenir");
        String taxArea = "CAArea";

        StoreSales _sales = new StoreSales(productAndPrices, taxAreaAndRates, productTaxAreaAndReduction);
        assertEquals(79.625, _sales.getTotalPrice(products, taxArea));
    }

    public void testGetTotalPriceAProductThatIsNotSoldInStore()
    {
        List<String> products = Arrays.asList("Watch");
        String taxArea = "TXArea";

        StoreSales _sales = new StoreSales(productAndPrices, taxAreaAndRates, productTaxAreaAndReduction);
        assertEquals(0.0, _sales.getTotalPrice(products, taxArea));
    }

    public void testGetTotalPriceForEmptyProductsList()
    {
        List<String> products = Arrays.asList(" ");
        String taxArea = "TXArea";

        StoreSales _sales = new StoreSales(productAndPrices, taxAreaAndRates, productTaxAreaAndReduction);
        assertEquals(0.0, _sales.getTotalPrice(products, taxArea));
    }

    public void testGetTotalPriceForEmptyTaxArea()
    {
        List<String> products = Arrays.asList("TShirt");
        String taxArea = "";

        StoreSales _sales = new StoreSales(productAndPrices, taxAreaAndRates, productTaxAreaAndReduction);
        assertEquals(0.0, _sales.getTotalPrice(products, taxArea));
    }

    public void testGetTotalPriceForEmptyTaxAreaAndEmptyProductsList()
    {
        List<String> products = Arrays.asList(" ");
        String taxArea = "";

        StoreSales _sales = new StoreSales(productAndPrices, taxAreaAndRates, productTaxAreaAndReduction);
        assertEquals(0.0, _sales.getTotalPrice(products, taxArea));
    }

    public void testGetTotalPriceForInvalidTaxArea()
    {
        List<String> products = Arrays.asList("TShirt");
        String taxArea = "COArea";

        StoreSales _sales = new StoreSales(productAndPrices, taxAreaAndRates, productTaxAreaAndReduction);
        assertEquals(0.0, _sales.getTotalPrice(products, taxArea));
    }
}
