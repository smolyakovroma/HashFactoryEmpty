package ru.hashfactory.empty.controller;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.hashfactory.empty.domain.User;
import ru.hashfactory.empty.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping("/cabinet")
@SessionAttributes("user")
public class DashboardController {

    @Autowired
    private UserService userService;

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
                    result = result.substring(1, result.length()-3) + "$";
                }
                if (output.indexOf("percent_change_24h") > 0) {
                    String res = output.substring(output.indexOf(":") + 2);
                    result = result + " ("+res.substring(1, res.length()-3)+"%)";
                }
                if (output.indexOf("percent_change_24h") > 0) {
                    rise = (output.indexOf("-") > 0?false:true);
                }
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Pair<>((coin + " "+result), rise);
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByEmail(principal.getUsername());
        modelAndView.getModel().put("user", user);
        Pair<String, Boolean> btc = getBitcoinCurrency("BTC");
        modelAndView.getModel().put("btc", btc.getKey());
        modelAndView.getModel().put("btc_rise", btc.getValue());
        Pair<String, Boolean> eth = getBitcoinCurrency("ETH");
        modelAndView.getModel().put("eth", eth.getKey());
        modelAndView.getModel().put("eth_rise", eth.getValue());
        modelAndView.setViewName("cabinet/dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "/ferms", method = RequestMethod.GET)
    public ModelAndView dashboardFerms(@SessionAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", user);
        Pair<String, Boolean> btc = getBitcoinCurrency("BTC");
        modelAndView.getModel().put("btc", btc.getKey());
        modelAndView.getModel().put("btc_rise", btc.getValue());
        Pair<String, Boolean> eth = getBitcoinCurrency("ETH");
        modelAndView.getModel().put("eth", eth.getKey());
        modelAndView.getModel().put("eth_rise", eth.getValue());
        modelAndView.setViewName("cabinet/ferms");
        return modelAndView;
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settings(@SessionAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", user);
        Pair<String, Boolean> btc = getBitcoinCurrency("BTC");
        modelAndView.getModel().put("btc", btc.getKey());
        modelAndView.getModel().put("btc_rise", btc.getValue());
        Pair<String, Boolean> eth = getBitcoinCurrency("ETH");
        modelAndView.getModel().put("eth", eth.getKey());
        modelAndView.getModel().put("eth_rise", eth.getValue());
        modelAndView.setViewName("cabinet/settings");
        return modelAndView;
    }
}