package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CountryMethod {

    /**
     * The following code is method for the region
     */
    public ArrayList<country> region_data(Connection con, String regionn)
    {
        try
        {
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country, city "
                            + "WHERE country.Capital = city.ID AND country.Region = ? "
                            + "ORDER BY Region ASC ,Population DESC ";

            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(strSelect);
            stmt.setString(1,regionn);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery();
            // Extract employee information
            ArrayList<country> countries = new ArrayList<country>();
            while (rset.next())
            {
                country cou = new country();
                cou.setCountry_code(rset.getString("country.Code"));
                cou.setCountry_name(rset.getString("country.Name"));
                cou.setContinent(rset.getString("country.Continent"));
                cou.setRegion(rset.getString("country.Region"));
                cou.setPopulation(rset.getInt("country.Population"));
                cou.setCity_name(rset.getString("city.Name"));
                countries.add(cou);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }
}

