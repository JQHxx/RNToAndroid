import React from 'react';
import { 
    AppRegistry, 
    StyleSheet, 
    Text, 
    View,
    NativeModules,
    TouchableOpacity
} from 'react-native';

import CardView from 'rn-cardview'


class HelloWorld extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            message: "Hello word!"
        }
    }

  render() {
    return (

        <View style={styles.container}>
            <TouchableOpacity 
                onPress={() => {
                    this.setState({
                        message: "Hello w!"
                    })
                    NativeModules.ToastForAndroid.show(1000)
                }}>
                    <Text style={styles.hello}>{this.state.message}</Text>
                    <CardView cardElevation={4}
                          maxCardElevation={4}
                          radius={10}
                          backgroundColor={'#ffffff'}>
                      <View style={{padding:10}}>
                          <View>
                              <Text>ReactNative CardView for iOS and Android</Text>
                          </View>
                          <View>
                              <Text>This is test</Text>
                          </View>
                      </View>
                </CardView>
            </TouchableOpacity>
        </View>

    );
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
    borderWidth: 1,
    borderColor: '#aaa',
    backgroundColor:'transparent'
  },
});
AppRegistry.registerComponent('RNHighScores', () => HelloWorld);