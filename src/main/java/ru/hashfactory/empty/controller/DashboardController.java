package ru.hashfactory.empty.controller;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.hashfactory.empty.domain.*;
import ru.hashfactory.empty.service.CabinetService;
import ru.hashfactory.empty.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("/cabinet")
@SessionAttributes("user")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private CabinetService cabinetService;

    public Pair<String, Boolean> getBitcoinCurrency(String coin) {

        String result = " 0.0$";
        Boolean rise = true;
        try {
            URL url = null;
            if ("BTC".equals(coin)) {
                url = new URL("https://api.coinmarketcap.com/v1/ticker/bitcoin/?convert=USD");
            } else if ("ETH".equals(coin)) {
                url = new URL("https://api.coinmarketcap.com/v1/ticker/ethereum/?convert=USD");
            } else {
                return new Pair<>((coin + result), rise);
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
                return new Pair<>((coin + result), rise);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                if (output.indexOf("price_usd") > 0) {
                    result = output.substring(output.indexOf(":") + 2);
                    result = result.substring(1, result.length() - 3) + "$";
                }
                if (output.indexOf("percent_change_24h") > 0) {
                    String res = output.substring(output.indexOf(":") + 2);
                    result = result + " (" + res.substring(1, res.length() - 3) + "%)";
                }
                if (output.indexOf("percent_change_24h") > 0) {
                    rise = (output.indexOf("-") > 0 ? false : true);
                }
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Pair<>((coin + " " + result), rise);
    }

    public float getCurrency() {
        float result = 0.0f;
        URL url = null;
        try {
            url = new URL("https://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance.xchange+where+pair+=+%22USDRUB%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
                return result;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                if (output.indexOf("Rate") > 0) {
                    result = Float.parseFloat(output.substring(output.indexOf("Rate") + 7,output.indexOf("Rate") + 14));
                }

            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
        e.printStackTrace();
    }

        return result;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User user = getPrincipalUser();
        modelAndView.getModel().put("user", user);
        modelAndView = getCurrencyForPage(modelAndView);
        modelAndView.setViewName("cabinet/dashboard");
        return modelAndView;
    }

    private User getPrincipalUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findUserByEmail(principal.getUsername());
    }

    @RequestMapping(value = "/ferms", method = RequestMethod.GET)
    public ModelAndView dashboardFerms(@SessionAttribute User user) {
        if (user == null) user = getPrincipalUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", user);
        modelAndView = getCurrencyForPage(modelAndView);
        modelAndView.setViewName("cabinet/ferms");
        return modelAndView;
    }

    @RequestMapping(value = "/invest", method = RequestMethod.GET)
    public ModelAndView invest(@SessionAttribute User user) {
        if (user == null) user = getPrincipalUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", user);
        modelAndView = getCurrencyForPage(modelAndView);
        modelAndView.setViewName("cabinet/invest");
        return modelAndView;
    }


    @RequestMapping(value = "/invest_calc", method = RequestMethod.GET)
//    public ModelAndView investCalc(@SessionAttribute User user) {
//        if(user==null) user = getPrincipalUser();
    //TODO на время отладки
    public ModelAndView investCalc() {
        User user = getPrincipalUser();
        ModelAndView modelAndView = new ModelAndView();
        Message message = cabinetService.findMessageById(1);
        modelAndView.getModel().put("message", message);
        modelAndView.getModel().put("user", user);
        modelAndView.getModel().put("ferms", cabinetService.findAllFerm());
        modelAndView = getCurrencyForPage(modelAndView);
        modelAndView.setViewName("cabinet/invest_calc");
        return modelAndView;
    }

    @RequestMapping(value = "/invest_calc/{name}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<CompoudFerm>> getFermByName(@PathVariable String name) {
        Ferm ferm = cabinetService.findByName(name);
        List<CompoudFerm> spec = cabinetService.findByFermNameOrderByOrd(name);
        return new ResponseEntity<List<CompoudFerm>>(spec, HttpStatus.OK);

    }

    @RequestMapping(value = "/documents", method = RequestMethod.GET)
    public ModelAndView documents(@SessionAttribute User user) {
        if (user == null) user = getPrincipalUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", user);
        modelAndView = getCurrencyForPage(modelAndView);
        modelAndView.setViewName("cabinet/documents");
        return modelAndView;
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settings(@SessionAttribute User user) {
        if (user == null) user = getPrincipalUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", user);
        modelAndView = getCurrencyForPage(modelAndView);
        modelAndView.setViewName("cabinet/settings");
        return modelAndView;
    }

    ModelAndView getCurrencyForPage(ModelAndView modelAndView) {
        Pair<String, Boolean> btc = getBitcoinCurrency("BTC");
        modelAndView.getModel().put("btc", btc.getKey());
        modelAndView.getModel().put("btc_rise", btc.getValue());
        Pair<String, Boolean> eth = getBitcoinCurrency("ETH");
        modelAndView.getModel().put("eth", eth.getKey());
        modelAndView.getModel().put("eth_rise", eth.getValue());
        return modelAndView;
    }

}