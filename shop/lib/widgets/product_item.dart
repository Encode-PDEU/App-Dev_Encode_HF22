import 'package:flutter/material.dart';

class ProductItem extends StatelessWidget {
  final String title, description, imageURL;

  const ProductItem({
    required this.title,
    required this.description,
    required this.imageURL,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(10),
      child: GridTile(
        footer: GridTileBar(
          backgroundColor: Colors.black87,
          leading: IconButton(
            onPressed: () {},
            icon: const Icon(Icons.favorite_border_rounded),
            color: Theme.of(context).colorScheme.secondary,
          ),
          title: Text(
            title,
            textAlign: TextAlign.center,
          ),
          trailing: IconButton(
            onPressed: () {},
            icon: const Icon(Icons.add_shopping_cart_rounded),
            color: Theme.of(context).colorScheme.secondary,
          ),
        ),
        child: Image.network(
          imageURL,
          fit: BoxFit.cover,
        ),
      ),
    );
  }
}
