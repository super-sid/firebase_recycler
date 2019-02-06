'use strict';
const functions = require('firebase-functions');
const admin = require('firebase-admin');
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });
const MAX_COUNT = 10;
var adminApp = admin.initializeApp(functions.config.firebase);

exports.truncate = functions.database.ref('/Users/{UserID}').onWrite(async (change, context) => {
  const parentRef = change.after.ref.parent;
  const snapshot =  await parentRef.once('value');
  if (snapshot.numChildren() >= MAX_COUNT) {
    let childCount = 0;
    const updates = {};
    snapshot.forEach((child) => {
      if (++childCount <= snapshot.numChildren() - MAX_COUNT) {
        updates[child.key] = null;
        console.log(child.key);
      }
    });
    // Update the parent. This effectively removes the extra children.
    return parentRef.update(updates);
  }
  return null;
});
