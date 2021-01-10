package dev.vasishta.invest.track.dao.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Dashboard;
import dev.vasishta.invest.track.bean.DashboardDetails;
import dev.vasishta.invest.track.config.DBConfig;
import dev.vasishta.invest.track.constant.GenericConstants;
import dev.vasishta.invest.track.constant.MessageType;
import dev.vasishta.invest.track.constant.ResponseMessages;
import dev.vasishta.invest.track.dao.DashboardDAO;
import dev.vasishta.invest.track.dao.Queries;
import dev.vasishta.invest.track.util.DBUtils;
import dev.vasishta.invest.track.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DashboardDAOImpl implements DashboardDAO, Queries {

    @Autowired
    private DBConfig dbConfig;

    @Autowired
    RestTemplate template;

    @Override
    public void getDashboard(BaseBean baseBean) {
        Dashboard dashboard = new Dashboard();
        try (Connection con = dbConfig.getDataSource().getConnection();) {
            getTotalInvestments(con, dashboard);
            getTotalWithdrew(con, dashboard);
            getTotalTaxes(con, dashboard);
            getTotalBrokerage(con, dashboard);
            getTotalProfit(con, dashboard);
            getTotalMargin(con, dashboard);
            getOtherDetails(con, dashboard);
            roundValues(dashboard);
            baseBean.setResponse(dashboard);
        } catch (SQLException ex) {
            ex.printStackTrace();
            baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.GET_DB_FAIL, MessageType.FAIL));
        }
    }

    private void getTotalMargin(Connection con, Dashboard dashboard) {
    }

    private void getTotalProfit(Connection con, Dashboard dashboard) throws SQLException {
        double totalProfit = 0, totalInvestment = 0;
        try (PreparedStatement statement = con.prepareStatement(TOTAL_PROFIT_MARGIN)) {
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                int buyQty = resultSet.getInt(1);
                int sellQty = resultSet.getInt(2);
                double buyNet = resultSet.getDouble(3);
                double sellNet = resultSet.getDouble(4);
                double investment = getInvestment(buyQty, sellQty, buyNet, sellNet);
                totalInvestment += investment;
                double profit = getProfitForTrade(sellNet, investment);
                totalProfit += profit;
                count += 1;
            }
            dashboard.setTotalProfit(totalProfit);
            dashboard.setTotalMargin((totalProfit / totalInvestment) * 100);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private double getInvestment(int buyQty, int sellQty, double buyNet, double sellNet) {
        return ((buyNet / buyQty) * sellQty);
    }

    private double getProfitForTrade(double sellNet, double investment) {
        return sellNet - investment;
    }

    private void getOtherDetails(Connection con, Dashboard dashboard) throws SQLException {
        List<DashboardDetails> dbDetailsList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(DB_OTHER_DETAILS);) {
            System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dbDetailsList.add((DashboardDetails) DBUtils.resultSetToPojo(rs, DashboardDetails.class));
            }
            calculateDashboardValues(dbDetailsList, dashboard);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void calculateDashboardValues(List<DashboardDetails> dbDetailsList, Dashboard dashboard) {
        double currentValueBT = 0, netCurrentValue = 0, currentInvestments = 0, currentTaxes = 0, currentBrokerage = 0;
        Map<String, Double> currentPriceMap = new HashMap<>();
        for (DashboardDetails dashboardDetails : dbDetailsList) {
            double currentPrice = getCurrentPrice(dashboardDetails, currentPriceMap);
            currentValueBT += dashboardDetails.getBalQty() * currentPrice;
            netCurrentValue += getNetCurValForOneItem(dashboardDetails, currentPrice);
            if (dashboardDetails.getBalQty() == dashboardDetails.getQty()) {
                currentInvestments += dashboardDetails.getNet();
                currentTaxes += dashboardDetails.getTaxes();
                currentBrokerage += dashboardDetails.getBrokerageAmount();
            } else {
                double pbt = dashboardDetails.getBalQty() * dashboardDetails.getPrice();
                double brokerageAmount = (pbt * dashboardDetails.getBrokerage()) / 100;
                double taxes = ((pbt + brokerageAmount) * dashboardDetails.getTaxes()) / (dashboardDetails.getPbt() + dashboardDetails.getBrokerageAmount());
                currentInvestments += (pbt + brokerageAmount + taxes);
                currentTaxes += taxes;
                currentBrokerage += brokerageAmount;
            }
        }
        dashboard.setCurrentValueBT(currentValueBT);
        dashboard.setNetCurrentValue(netCurrentValue);
        dashboard.setCurrentInvestments(currentInvestments);
        dashboard.setCurrentTaxes(currentTaxes);
        dashboard.setCurrentBrokerage(currentBrokerage);
        dashboard.setCurrentPL(netCurrentValue - currentInvestments);
        dashboard.setCurrentMargin(((netCurrentValue - currentInvestments) / currentInvestments) * 100);
    }

    private void roundValues(Dashboard dashboard) {
        dashboard.setTotalInvestments(Math.round(dashboard.getTotalInvestments() * 100.0) / 100.0);
        dashboard.setTotalWithdrew(Math.round(dashboard.getTotalWithdrew() * 100.0) / 100.0);
        dashboard.setTotalTaxes(Math.round(dashboard.getTotalTaxes() * 100.0) / 100.0);
        dashboard.setTotalBrokerage(Math.round(dashboard.getTotalBrokerage() * 100.0) / 100.0);
        dashboard.setCurrentInvestments(Math.round(dashboard.getCurrentInvestments() * 100.0) / 100.0);
        dashboard.setCurrentTaxes(Math.round(dashboard.getCurrentTaxes() * 100.0) / 100.0);
        dashboard.setCurrentBrokerage(Math.round(dashboard.getCurrentBrokerage() * 100.0) / 100.0);
        dashboard.setCurrentValueBT(Math.round(dashboard.getCurrentValueBT() * 100.0) / 100.0);
        dashboard.setNetCurrentValue(Math.round(dashboard.getNetCurrentValue() * 100.0) / 100.0);
        dashboard.setCurrentPL(Math.round(dashboard.getCurrentPL() * 100.0) / 100.0);
        dashboard.setCurrentMargin(Math.round(dashboard.getCurrentMargin() * 100.0) / 100.0);
        dashboard.setTotalProfit(Math.round(dashboard.getTotalProfit() * 100.0) / 100.0);
        dashboard.setTotalMargin(Math.round(dashboard.getTotalMargin() * 100.0) / 100.0);
    }

    private double getNetCurValForOneItem(DashboardDetails dashboardDetails, double currentPrice) {
        double pbt = dashboardDetails.getBalQty() * currentPrice;
        double brokerageAmount = (pbt * dashboardDetails.getBrokerage()) / 100.0;
        double taxes = ((pbt + brokerageAmount) * dashboardDetails.getTaxes()) / (dashboardDetails.getPbt() + dashboardDetails.getBrokerageAmount());
        return (pbt - brokerageAmount - taxes);
    }

    private double getCurrentPrice(DashboardDetails dashboardDetails, Map<String, Double> currentPriceMap) {
        if (currentPriceMap.containsKey(dashboardDetails.getEquitySymbol()))
            return currentPriceMap.get(dashboardDetails.getEquitySymbol());
        else {
            String url = GenericConstants.MONEY_CONTROL_API + dashboardDetails.getExchange().toLowerCase() + "/equitycash/" + dashboardDetails.getMcSymbol();
            ResponseEntity<String> response = template.getForEntity(url, String.class);
            JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
            double currentPrice = Double.parseDouble(jsonObject.getAsJsonObject("data").getAsJsonPrimitive("pricecurrent").getAsString());
            currentPriceMap.put(dashboardDetails.getEquitySymbol(), currentPrice);
            return currentPrice;
        }
    }

    private void getTotalBrokerage(Connection con, Dashboard dashboard) throws SQLException {
        try (PreparedStatement statement = con.prepareStatement(DB_TOTAL_BROKERAGE);) {
            System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                double totalBrokerage = rs.getDouble(1);
                dashboard.setTotalBrokerage(totalBrokerage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void getTotalTaxes(Connection con, Dashboard dashboard) throws SQLException {
        try (PreparedStatement statement = con.prepareStatement(DB_TOTAL_TAXES);) {
            System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                double totalTaxes = rs.getDouble(1);
                dashboard.setTotalTaxes(totalTaxes);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void getTotalWithdrew(Connection con, Dashboard dashboard) throws SQLException {
        try (PreparedStatement statement = con.prepareStatement(DB_TOTAL_WITHDREW);) {
            System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                double totalWithdrew = rs.getDouble(1);
                dashboard.setTotalWithdrew(totalWithdrew);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    private void getTotalInvestments(Connection con, Dashboard dashboard) throws SQLException {
        try (PreparedStatement statement = con.prepareStatement(DB_TOTAL_INVESTMENTS);) {
            ResultSet rs = statement.executeQuery();
            System.out.println(statement.toString());
            if (rs.next()) {
                double totalInvested = rs.getDouble(1);
                dashboard.setTotalInvestments(totalInvested);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
