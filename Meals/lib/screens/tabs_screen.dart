import 'package:flutter/material.dart';

import './categories_screen.dart';
import './favorites_screen.dart';

class TabsScreen extends StatefulWidget {
  @override
  _TabsScreenState createState() => _TabsScreenState();
}

class _TabsScreenState extends State<TabsScreen> {
  void _selectPage(int index) {}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Meals'),
        ),
        //   body: null,
        //   bottomNavigationBar: BottomNavigationBar(
        //     onTap: _selectPage,
        //     backgroundColor: Theme.of(context).primaryColor,
        //     items: [
        //       BottomNavigationBarItem(
        //         icon: Icon(Icons.category),
        //         title: Text('Categories'),
        //       ),
        //       BottomNavigationBarItem(
        //         icon: Icon(Icons.star),
        //         title: Text('Favorites'),
        //       ),
        //     ],
        //   ),
        // );
        body: DefaultTabController(
          length: 2,
          // initialIndex: 1, //default 0 //sets which tab to show first
          child: Scaffold(
            appBar: AppBar(
              title: Text('Meals'),
              bottom: TabBar(
                tabs: <Widget>[
                  Tab(
                    icon: Icon(Icons.category),
                    text: 'Categories',
                  ),
                  Tab(
                    icon: Icon(Icons.star),
                    text: 'Favorites',
                  ),
                ],
              ),
            ),
            body: TabBarView(
              children: <Widget>[
                CategoriesScreen(),
                FavoritesScreen(),
              ],
            ),
          ),
        ));
  }
}
