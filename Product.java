import java.util.List;

class Product {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class InvalidProductException extends RuntimeException {
    public InvalidProductException(String message) {
        super(message);
    }
}

class InvoiceCalculator {
    public static double calculateInvoice(List<Product> products, double discount) {
        if (products.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (Product product : products) {
            if (product.price < 0) {
                throw new InvalidProductException("Preço inválido.");
            }
            if (product.quantity < 0) {
                throw new InvalidProductException("Quantidade inválida.");
            }
            total += product.price * product.quantity;
        }

        double totalWithDiscount = total * (1 - discount / 100);

        if (total > 1000.0) {
            totalWithDiscount -= 100.0;
        }

        return Math.round(totalWithDiscount * 100.0) / 100.0;
    }
}
