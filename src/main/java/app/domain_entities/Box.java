package app.domain_entities;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by jcramer on 4/27/2017.
 */
public class Box {

    private final boolean isDisplayed;
    private final String headerText;
    private final boolean isFirstNameFieldAvailable;

    public Box(boolean isDisplayed, String headerText, boolean isFirstNameFieldAvailable) {
        this.isDisplayed = isDisplayed;
        this.headerText = headerText;
        this.isFirstNameFieldAvailable = isFirstNameFieldAvailable;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(isDisplayed)
                .append(headerText)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Box
                && this.isDisplayed == ((Box) obj).isDisplayed
                && this.headerText.equals(((Box) obj).headerText);
    }

    @Override
    public String toString() {
        return String.format("Is Displayed: %s; Header Text: %s", isDisplayed, headerText);
    }
}
