import React from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    Animated,
    FlatList,
    //需要导入NativeModules组件，这个是官方提供给我们与原生交互的组件，通过它我们才能调用到原生的方法
    NativeModules
} from 'react-native';
import { TouchableOpacity } from 'react-native-gesture-handler';
// 看到 RTModule 是不是很熟悉，没错这个就是原生中写的那个类
// 后面一定要一样哦
var RNModules  = NativeModules.RTModule;

const AnimatedFlatList = Animated.createAnimatedComponent(FlatList);

// RN主入口文件
class RNHighScores extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            filteredData: ['啊飒飒是方法', 'saasafsafasf', 'das阿萨德撒飞洒发生发顺丰']
        };
    }

    render() {
        return (
            <View style={styles.container}>
                <AnimatedFlatList
                    renderItem={this._renderItemComponent.bind(this)}
                    horizontal={false}
                    data={this.state.filteredData}
                    numColumns={1}
                    keyExtractor={(item, index)=>index}
                    bounces={false}
                />
            </View>
        );
    }

    _renderItemComponent({item, index}) {
        return (
            <TouchableOpacity style={styles.itemView}
            onPress={() => {
                RNModules.RNOpenOneVC('测试')
            }}>
                <Text style={styles.itemText}>{item}</Text>
            </TouchableOpacity>
        )
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#FFFFFF',
    },
    itemView: {
        width: 300,
        height: 100,
        backgroundColor: 'red',
        marginBottom: 10,
        justifyContent: 'center',
        alignItems: 'center'
    },
    itemText: {
        color: 'white',
        fontSize: 17,
        textAlign: 'center'
    }

});

// 整体js模块的名称
AppRegistry.registerComponent('RNHighScores', () => RNHighScores);