//
//  CircularSpeedo.swift
//  iosApp
//
//  Created by Radosław Czubak on 16/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CircularSpeedo: View {
    let value: Double
    let upLimit: Double = 50.0
    let lowLimit: Double = 0.0
    let fontSize: Int
    let size: Int
    
    init(value: Double){
        self.value = value
        self.fontSize = 80
        self.size = 300
    }
    
    init(value: Double, fontSize: Int, size: Int) {
        self.value = value
        self.fontSize = fontSize
        self.size = size
    }
    
    var body: some View {
        Gauge(value: value, in: lowLimit...upLimit){
            Image(systemName: "gauge.medium")
                .font(.system(size: 50.0))
        } currentValueLabel: {
            Text(String(format: "%.0f", value))

        } .gaugeStyle(
            CircularGaugeStyle(
            width: size, height: size,
            fontSize: fontSize)
        )
    }
}

struct CircularSpeedo_Previews: PreviewProvider {
    static var previews: some View {
        CircularSpeedo(value: 23)
    }
}
