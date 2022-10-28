import 'package:flutter/material.dart';

class ProductDetailsScreen extends StatelessWidget {
  static const String routeName = '/product-details';
  const ProductDetailsScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // final String id = ModalRoute.of(context).settings.arguments;
    return Scaffold(
      appBar: AppBar(
        title: const Text('title'),
      ),
    );
  }
}
