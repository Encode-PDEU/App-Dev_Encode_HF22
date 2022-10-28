import 'package:flutter/widgets.dart';
import 'package:shop/models/product.dart';

class Products with ChangeNotifier {
  List<Product> _items = [];

  List<Product> get items {
    return [..._items];
  }
}
