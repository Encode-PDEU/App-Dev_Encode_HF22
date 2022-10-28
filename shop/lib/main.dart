import 'package:flutter/material.dart';
import 'package:shop/screens/product_details_screen.dart';
import 'package:shop/screens/products_overview_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Jay Nakum',
      theme: ThemeData(
        // primarySwatch: Colors.purple,
        colorScheme: const ColorScheme(
          brightness: Brightness.light,
          primary: Colors.purple,
          onPrimary: Colors.white,
          secondary: Colors.deepOrange,
          onSecondary: Colors.white,
          error: Colors.white,
          onError: Colors.red,
          background: Colors.white,
          onBackground: Colors.black,
          surface: Colors.white,
          onSurface: Colors.black,
        ),
        fontFamily: 'Lato',
      ),
      home: const ProductsOverviewScreen(),
      routes: {
        ProductsOverviewScreen.routeName: (context) =>
            const ProductsOverviewScreen(),
        ProductDetailsScreen.routeName: (context) =>
            const ProductDetailsScreen(),
      },
    );
  }
}
