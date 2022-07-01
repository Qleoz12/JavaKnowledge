package chap10;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modernjavainaction.chap10.dsl.model.Order;
import modernjavainaction.chap10.dsl.model.Stock;
import modernjavainaction.chap10.dsl.model.Trade;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BuyStocksSteps {

    private Map<String, Integer> stockUnitPrices = new HashMap<>();
    private Order order = new Order();

    @Given("^the price of a \"(.*?)\" stock is (\\d+)\\$$")
    public void setUnitPrice(String stockName, int unitPrice) {
        stockUnitPrices.put(stockName, unitPrice);
    }

    @When("^I buy (\\d+) \"(.*?)\"$")
    public void buyStocks(int quantity, String stockName) {
        Trade trade = new Trade();
        trade.setType(Trade.Type.BUY);
        Stock stock = new Stock();
        stock.setSymbol(stockName);
        trade.setStock(stock);
        trade.setPrice(stockUnitPrices.get(stockName));
        trade.setQuantity(quantity);
        order.addTrade(trade);
    }


    @Then("^the order value should be (\\d+)\\$$")
    public void checkOrderValue(int expectedValue) {
        assertEquals(expectedValue, order.getValue());
    }

}


