import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_storage/firebase_storage.dart';

class Auth {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  // final FirebaseApp staff = Firebase.app('Staff');
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  final FirebaseStorage _storage = FirebaseStorage.instance;

  FirebaseAuth get auth {
    return _auth;
  }

  FirebaseFirestore get firestore {
    return _firestore;
  }

  FirebaseStorage get storage {
    return _storage;
  }

  Future<void> login({
    required String email,
    required String password,
  }) async {
    String message = 'Logged in';
    try {
      await _auth.signInWithEmailAndPassword(
        email: email,
        password: password,
      );
    } on FirebaseAuthException catch (e) {
      if (e.code == 'user-not-found') {
        message = 'No user found for that email.';
        print(message);
      } else if (e.code == 'wrong-password') {
        message = 'Wrong password provided for that user.';
        print(message);
      }
    } catch (err) {
      message = err.toString();
      print(err);
    }
  }

  Future<void> register({
    required String email,
    required String password,
    String username = '',
  }) async {
    String message = 'User Created';
    try {
      // UserCredential _authResult =
      await _auth.createUserWithEmailAndPassword(
        email: email,
        password: password,
      );
      await _auth.currentUser!.updateDisplayName(username);
      // _setUserDetails(authResult, username, email);
    } on FirebaseAuthException catch (e) {
      if (e.code == 'weak-password') {
        message = 'The password provided is too weak.';
      } else if (e.code == 'email-already-in-use') {
        message = 'The account already exists for that email.';
      }
    } catch (err) {
      message = err.toString();
    }
    print(message);
  }

  // void _setUserDetails(
  //   UserCredential authResult,
  //   String username,
  //   String email,
  //   // File image,
  // ) async {
  //   String message = 'User Created';
  //   try {
  //     // final ref = _storage
  //     //     .ref()
  //     //     .child(
  //     //       'user_images',
  //     //     )
  //     //     .child(
  //     //       authResult.user!.uid + '.jpg',
  //     //     );
  //     // await ref.putFile(image).whenComplete(() => null);
  //     // final url = await ref.getDownloadURL();
  //     await _auth.currentUser!.updateDisplayName(username);
  //     await _firestore.collection('users').doc(email).set({
  //       'username': username,
  //       'email': email,
  //       // 'image_url': url,
  //     });
  //   } catch (err) {
  //     message = err.toString();
  //   }
  //   print(message);
  //   // return message;
  // }

  void refresh() async {
    await _auth.currentUser!.reload();
  }
}
