import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';

import './auth.dart';
import './auth_screen.dart';

import 'package:staff/screens/home_screen.dart'; // TODO: Your Home Screen

class Index extends StatelessWidget {
  const Index({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: Firebase.initializeApp(),
      builder: (ctx, snapshot) {
        return Scaffold(
          body: snapshot.hasData
              ? StreamBuilder(
                  stream: FirebaseAuth.instance.userChanges(),
                  builder: (ctx, userSnapshot) {
                    if (userSnapshot.hasData &&
                        Auth().auth.currentUser!.emailVerified) {
                      return HomeScreen(); // TODO: Your Home Screen
                    }
                    return AuthScreen();
                  },
                )
              : Center(child: CircularProgressIndicator()),
        );
      },
    );
  }
}
