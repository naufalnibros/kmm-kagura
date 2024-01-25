//
//  SkinScreen.swift
//  iosApp
//
//  Created by naufalnibros on 24/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Foundation
import SDWebImageSwiftUI

struct SkinScreen: View {
    @ObservedObject var state = ViewModelProvider().getSkinViewModel().asObserveableObject()
    
    var body: some View {
        VStack(spacing: 0){
            ScrollView(showsIndicators: false) {
                VStack {
                    switch state.stateList {
                    case is StateSkinOnSuccess:
                        contentView(result: state.stateList as! StateSkinOnSuccess)
                    case is StateSkinOnError:
                        errorView()
                    default:
                        loadingView()
                    }
                }
            }
            
        }
        .background(Color.black.ignoresSafeArea())
        .navigationBarHidden(true)
        
        
    }
    
    @ViewBuilder func contentView(result: StateSkinOnSuccess) -> some View {
        VStack(alignment: .leading){
            Text("Mba Kagura Uwu :3")
                .foregroundColor(Color.white)
                .fontWeight(Font.Weight.bold)
                .padding(.horizontal)
            Text("Select your favorites kagura skin")
                .foregroundColor(Color.gray)
                .padding(.horizontal)
            skinPosterView(list: result.data)
            Spacer().frame(height: 32)
            Text("Select skin poster to see detail :")
                .foregroundColor(Color.white)
                .fontWeight(Font.Weight.bold)
                .padding(.horizontal)
            Spacer().frame(height: 24)
            skinDetailView(item: state.stateSelected)
            
        }
    }
    
    @ViewBuilder func loadingView() -> some View {
        VStack(alignment: .center){
            HStack{
                Spacer()
            }
            Text("Loading ...")
                .foregroundColor(Color.white)
        }.background(Color.black)
        
    }
    
    @ViewBuilder func errorView() -> some View {
        Spacer()
        VStack(alignment: .center){
            HStack{
                Spacer()
            }
            Text(":(")
                .foregroundColor(Color.white)
                .font(.system(size: 36))
            Spacer().frame(height: 16)
            Text("Something wrong")
                .foregroundColor(Color.white)
            Spacer().frame(height: 8)
            Button("Try again"){
                state.viewModel.load()
            }
        }.background(Color.black)
        
    }
    
    
    @ViewBuilder func skinPosterView(list: [Skin]) -> some View {
        ScrollView(.horizontal, showsIndicators: false){
            LazyHStack(alignment: VerticalAlignment.top){
                ForEach(list.indices) { position in
                    let item = list[position]
                    VStack(alignment: .leading){
                        WebImage(url: URL(string: item.poster))
                            .resizable()
                            .frame(width: 130, height: 200)
                            .cornerRadius(14)
                        Spacer().frame(height: 8)
                        Text(item.title).foregroundColor(Color.white)
                            .font(.system(size: 14))
                            .fontWeight(Font.Weight.bold)
                    }.frame(width: 135)
                        .onTapGesture {
                            state.viewModel.selected(item: item)
                        }
                }
            }
            
        }
    }
    
    
    @ViewBuilder func skinDetailView(item: Skin) -> some View {
        if item.id == 0 {
            Spacer()
        }else{
            VStack(alignment: .leading){
                ZStack(alignment: .leading){
                    VStack(alignment: .leading){
                        WebImage(url: URL(string: item.banner))
                            .resizable()
                            .frame(height: 160)
                        Text(item.title).foregroundColor(Color.white)
                            .font(.system(size: 14))
                            .fontWeight(Font.Weight.bold)
                            .padding(EdgeInsets(top: 8, leading: 135, bottom: 0, trailing: 16))
                    }
                  
                    WebImage(url: URL(string: item.poster))
                        .resizable()
                        .frame(width: 100, height: 140)
                        .cornerRadius(14)
                        .padding(EdgeInsets(top: 60, leading: 16, bottom: 0, trailing: 0))
                }
              
              
                
            }
            Text(item.info).foregroundColor(Color.white)
                .font(.system(size: 14))
                .padding(EdgeInsets(top:16, leading: 16, bottom: 0, trailing: 16))
        }
        
        
    }
    
}


struct SkinScreen_Previews: PreviewProvider {
    static var previews: some View {
        SkinScreen()
    }
}

