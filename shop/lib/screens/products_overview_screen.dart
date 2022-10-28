import 'package:flutter/material.dart';
import 'package:shop/widgets/product_item.dart';

import '../models/product.dart';

class ProductsOverviewScreen extends StatelessWidget {
  static const String routeName = '/products-overview';
  const ProductsOverviewScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('My Shop'),
      ),
      body: GridView.builder(
        padding: const EdgeInsets.all(10.0),
        itemCount: Products.loadedProducts.length,
        itemBuilder: (ctx, i) => ProductItem(
          title: Products.loadedProducts[i].title,
          description: Products.loadedProducts[i].description,
          imageURL: Products.loadedProducts[i].imageURL,
        ),
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          childAspectRatio: 3 / 2,
          crossAxisSpacing: 10,
          mainAxisSpacing: 10,
        ),
      ),
    );
  }
}
