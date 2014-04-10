//
//  AppDelegate.h
//
//  Created by Ghislain Bernier on 02/04/14.
//  Copyright (c) 2014 All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TapjoyWrapper.h"

@interface AppDelegate : UIResponder <UIApplicationDelegate>{
    TapjoyWrapper * tapjoyWrapper;
}

@property (strong, nonatomic) UIWindow *window;

@end
