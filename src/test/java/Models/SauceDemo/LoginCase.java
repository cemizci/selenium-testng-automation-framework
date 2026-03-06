package Models.SauceDemo;

public record LoginCase (
        String name,
        String username,
        String password,
        boolean expectedSuccess,
        String expectedErrorContains
){}

