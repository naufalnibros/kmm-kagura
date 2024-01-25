//
//  SkinObservable.swift
//  iosApp
//
//  Created by naufalnibros on 24/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

public class SkinObservable : ObservableObject {
    
    var viewModel : SkinViewModel
    
    /**
     *
     * state flow acts as a state for swift ui here
     *
     */
    @Published private(set) var stateList: StateSkin
    
    @Published private(set) var stateSelected: Skin
    
    /**
     **
     *fusing the .asObserveable extension funstion we get the wrapped viewmodel and the stateflow
     */
    init(wrapped: SkinViewModel) {
        viewModel = wrapped
        
        stateList = wrapped.skins.value as! StateSkin
        stateSelected = wrapped.select.value as! Skin
        
        (wrapped.skins.asPublisher() as AnyPublisher<StateSkin, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$stateList)
        
        (wrapped.select.asPublisher() as AnyPublisher<Skin, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$stateSelected)
    }
    
}


public extension SkinViewModel {
    func asObserveableObject() -> SkinObservable{
        return SkinObservable(wrapped: self)
    }
}
