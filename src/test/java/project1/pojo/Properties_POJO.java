package project1.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties_POJO {
    private String symbol;
    private String baseAsset;
    private String quoteAsset;
    private String openPrice;
    private String lowPrice;
    private String highPrice;
    private String lastPrice;
    private String volume;
    private String bidPrice;
    private String askPrice;
    private long at;
}
