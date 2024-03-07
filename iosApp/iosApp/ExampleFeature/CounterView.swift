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
import CoreLocation

struct CounterView: View {
    @StateViewModel var viewModel = shared.CounterViewModel()
    
    @StateViewModel var speedoViewModel: SpeedometerViewModel = shared.SpeedometerViewModel(locationRepository:
                                                                                                shared.LocationRepositoryImpl(locationManager: shared.KoinHelperKt.getLocationManager()))
    
    
    
    var body: some View {
        VStack(alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/){
            Text("Lat: \(speedoViewModel.state.lat) Lng: \(speedoViewModel.state.lon)")
            Button(action: {
                CLLocationManager().requestWhenInUseAuthorization()
            }) {
                Text("Check permission")
            }
            Button(action: {speedoViewModel.onEvent(speedometerEvent: SpeedometerEvent.CheckLocation())}) {
                Text("Check location")
            }
            
            
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
