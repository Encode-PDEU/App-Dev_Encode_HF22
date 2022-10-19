import 'package:flutter/material.dart';

import './auth.dart';

class AuthScreen extends StatefulWidget {
  const AuthScreen({Key? key}) : super(key: key);

  @override
  _AuthScreenState createState() => _AuthScreenState();
}

class _AuthScreenState extends State<AuthScreen> {
  final Auth _auth = Auth();
  final _formKey = GlobalKey<FormState>();
  var _userEmail = '';
  var _userName = '';
  var _userPassword = '';
  var _isLogin = true;
  var _isLoading = false;

  void _trySubmit() {
    final isValid = _formKey.currentState!.validate();
    FocusScope.of(context).unfocus();
    if (isValid) {
      _formKey.currentState!.save();
      try {
        setState(() {
          _isLoading = true;
        });
        if (_isLogin) {
          _auth.login(
            email: _userEmail.trim(),
            password: _userPassword.trim(),
          );
        } else {
          _auth
              .register(
                  email: _userEmail.trim(),
                  password: _userPassword.trim(),
                  username: _userName.trim())
              .then((_) async {
            await _auth.auth.currentUser!.sendEmailVerification();
          }).then((_) {
            showDialog(
              context: context,
              builder: (ctx) {
                return AlertDialog(
                  title: Text('Verification'),
                  content: Text(
                    'A verification link is sent to your email, please verify to proceed',
                  ),
                  actions: [
                    TextButton(
                      onPressed: () {
                        setState(() {
                          _isLoading = false;
                          _isLogin = true;
                        });
                        Navigator.of(context).pop();
                      },
                      child: Text('Okay'),
                    ),
                  ],
                );
              },
            );
          });
        }
      } catch (err) {
        setState(() {
          _isLoading = false;
        });
        print(err);
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Card(
          margin: const EdgeInsets.all(20.0),
          child: SingleChildScrollView(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Form(
                key: _formKey,
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: <Widget>[
                    // if(_isLogin) UserImagePicker(_pickedImage),
                    TextFormField(
                      key: ValueKey('Email'),
                      keyboardType: TextInputType.emailAddress,
                      decoration: InputDecoration(
                        labelText: 'Email Address',
                      ),
                      validator: (value) {
                        if (value!.isEmpty || !value.contains('@')) {
                          return 'Please enter a valid email address';
                        }
                        return null;
                      },
                      onSaved: (value) {
                        _userEmail = value!;
                      },
                    ),
                    if (!_isLogin)
                      TextFormField(
                        key: ValueKey('userName'),
                        validator: (value) {
                          if (value!.isEmpty || value.length < 3) {
                            return 'Please enter atleast 3 characters';
                          }
                          return null;
                        },
                        decoration: InputDecoration(
                          labelText: 'Username',
                        ),
                        onSaved: (value) {
                          _userName = value!;
                        },
                      ),
                    TextFormField(
                      key: ValueKey('password'),
                      validator: (value) {
                        if (value!.isEmpty || value.length < 7) {
                          // 7 is firebase default
                          return 'Password must be atleast 7 characters long.';
                        }
                        return null;
                      },
                      obscureText: true,
                      decoration: InputDecoration(
                        labelText: 'Password',
                      ),
                      onSaved: (value) {
                        _userPassword = value!;
                      },
                    ),
                    SizedBox(
                      height: 12,
                    ),
                    if (_isLoading) CircularProgressIndicator(),
                    if (!_isLoading)
                      OutlinedButton(
                        style: ButtonStyle(
                          backgroundColor: MaterialStateProperty.all(
                              Theme.of(context).primaryColor),
                        ),
                        onPressed: _trySubmit,
                        child: Text(
                          _isLogin ? 'Login' : 'Signup',
                          style: TextStyle(color: Colors.white),
                        ),
                      ),
                    if (!_isLoading)
                      TextButton(
                        onPressed: () {
                          setState(() {
                            _isLogin = !_isLogin;
                          });
                        },
                        child: Text(
                          _isLogin
                              ? 'Create new account!'
                              : 'I already have an account!',
                        ),
                      ),
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
