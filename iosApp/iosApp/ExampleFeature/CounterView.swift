//
//  CounterView.swift
//  iosApp
//
//  Created by Radosław Czubak on 04/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import KMMViewModelSwiftUI
import shared

struct CounterView: View {
    @StateViewModel var viewModel = shared.CounterViewModel()
    var body: some View {
        VStack(alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/){
            Text("Counter: \(viewModel.state.counter)")
            HStack{
                Button(action: {viewModel.onEvent(counterEvent: CounterEventDecrement())}) {
                    Text("Decrement")
                }
                Button(action: {viewModel.onEvent(counterEvent: CounterEventIncrement())}) {
                    Text("Increment")
                }
            }
        }
    }
}

#Preview {
    CounterView()
}
