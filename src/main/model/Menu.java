package src.main.model;

public class Menu {

    private String[] items;
    private String timeRange;
    private String price;
    private String imageName;

    public Menu(String[] items, String timeRange, String price, String imageName) {
            this.items = items;
            this.timeRange = timeRange;
            this.price = price;
            this.imageName = imageName;
    }

    public String[] getItems() {
        return items;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public String getDataPrice() {
        return price;
    }

    public String getDataImageName() {
        return imageName;
    }
}
