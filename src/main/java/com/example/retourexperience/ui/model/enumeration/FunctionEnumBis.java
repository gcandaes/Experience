package com.example.retourexperience.ui.model.enumeration;


public enum FunctionEnumBis {
    SERVEUR("1"),
    CHEF_DE_RANG("2"),
    PLONGEUR("3"),
    COMMIS_DE_CUISINE("4"),
    CHEF_CUISINIER("5"),
    BARMAN("6"),
    LIMONADIER("7"),
    SECOND_DE_CUISINE("8"),
    PIZZAIOLO("9"),
    RECEPTIONNISTE("10"),
    VALET("11"),
    FEMME_HOMME_DE_MENAGE("12"),
    VOITURIER("13");

    private final String value;

    FunctionEnumBis(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

 class FunctionEnumLabels {
    public static final String SERVEUR = "Serveur";
    public static final String CHEF_DE_RANG = "Chef de Rang";
    public static final String PLONGEUR = "Plongeur";
    public static final String COMMIS_DE_CUISINE = "Commis de Cuisine";
    public static final String CHEF_CUISINIER = "Chef Cuisinier";
    public static final String BARMAN = "Barman";
    public static final String LIMONADIER = "Limonadier";
    public static final String SECOND_DE_CUISINE = "Second de Cuisine";
    public static final String PIZZAIOLO = "Pizzaïolo";
    public static final String RECEPTIONNISTE = "Réceptionniste";
    public static final String VALET = "Valet";
    public static final String FEMME_HOMME_DE_MENAGE = "Femme/Homme de ménage";
    public static final String VOITURIER = "Voiturier";
}
